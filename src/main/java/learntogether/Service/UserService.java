package learntogether.Service;

import learntogether.Converter.UserConverter;
import learntogether.DTO.UserDTO;
import learntogether.Entity.PostEntity;
import learntogether.Entity.RoleEntity;
import learntogether.Entity.UserEnity;
import learntogether.IService.IUserService;
import learntogether.Repository.PostRepository;
import learntogether.Repository.UserRepository;
import learntogether.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
  Created by Luvbert
*/
@Service
@Transactional
public class UserService implements IUserService {
    private UserRepository userRepository;
    private UserConverter userConverter;
    private PostRepository postRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserConverter userConverter, PostRepository postRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.postRepository = postRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Map registerNewUserAccount(UserDTO userDTO) {
        Map<String, String> message = new HashMap<>();
        // Service validate
        if(isEmailExist(userDTO.getEmail())){
            message.put("email", "Email address is already registered !");
        }
        if(isUsernameExist(userDTO.getUsername())){
            message.put("username", "Username is already registered !");
        }
        if(userDTO.getUsername().length() < 6){
            message.put("username", "Username is too short !");
        }
        if(userDTO.getPassword().length() < 6){
            message.put("password", "Password is too short !");
        }
        //
        if(message.isEmpty()){
            userRepository.save(userConverter.toEntity(userDTO));
        }
        return message;
    }


    @Override
    public UserDTO findUserByUsername(String username) {
        UserDTO userDTO;
        UserEnity userEnity = userRepository.findByUsername(username);
        String currentUserLoggedName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(userEnity.getUsername().equals(currentUserLoggedName)){
            userDTO = userConverter.toDTO(userEnity);
        } else
        {
            userDTO = userConverter.toDTOSecure(userEnity);
        }
        userDTO.setNumberOfUserPost(postRepository.countAllByUser(userEnity));
        // get all score user got
        Integer totalScore = 0;
        List<PostEntity> posts = postRepository.findAllByUser(userEnity);
        for (int i = 0; i < posts.size(); i++) {
            totalScore += posts.get(i).getScore();
        }
        userDTO.setTotalScore(totalScore);
        //
        return userDTO;
    }

    @Override
    public UserDTO findUserByUserId(Long userId) {
        UserDTO userDTO;
        UserEnity userEnity = userRepository.findUserById(userId);
        String currentUserLoggedName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(userEnity.getUsername().equals(currentUserLoggedName)){
            userDTO = userConverter.toDTO(userEnity);
        } else
        {
            userDTO = userConverter.toDTOSecure(userEnity);
        }
        userDTO.setNumberOfUserPost(postRepository.countAllByUser(userEnity));
        // get all score user got
        Integer totalScore = 0;
        List<PostEntity> posts = postRepository.findAllByUser(userEnity);
        for (int i = 0; i < posts.size(); i++) {
            totalScore += posts.get(i).getScore();
        }
        userDTO.setTotalScore(totalScore);
        //
        return userDTO;
    }

    @Override
    public boolean isEmailExist(String email){
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public boolean isUsernameExist(String username){
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public boolean isUsernameAndPasswordMatch(String username, String password) {
        UserEnity userEnity = userRepository.findByUsername(username);
        if(userEnity == null) return false;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, userEnity.getPassword());
    }

    @Override
    public UserDTO changeUserQuote(UserDTO userDTO) throws Exception{
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(username == null){
            throw new Exception("User not login");
        }
        if(userDTO.getUserQuote() == null){
            throw new Exception("New user quote not to be empty");
        }
        UserEnity entity = userRepository.findByUsername(username);
        entity.setUserQuote(userDTO.getUserQuote());
        return userConverter.toDTO(userRepository.save(entity));
    }

    @Override
    public UserDTO changeUserPhoneOrFullname(UserDTO userDTO) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(username == null){
            throw new Exception("User not login");
        }
        if(userDTO.getPhoneNumber() == null && userDTO.getFullname() == null){
            throw new Exception("New user information not to be empty");
        }
        UserEnity entity = userRepository.findByUsername(username);
        if(userDTO.getPhoneNumber() != null){
            entity.setPhoneNumber(userDTO.getPhoneNumber());
        }
        if(userDTO.getFullname() != null){
            entity.setFullname(userDTO.getFullname());
        }
        return userConverter.toDTO(userRepository.save(entity));
    }

    @Override
    public UserDTO changeUserPassword(UserDTO userDTO) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(username == null){
            throw new Exception("User not login");
        }
        if(userDTO.getPassword() == null || userDTO.getNewPassword() == null){
            throw new Exception("Password not to be empty");
        }
        UserEnity entity = userRepository.findByUsername(username);
        if(passwordEncoder.matches(userDTO.getPassword(), entity.getPassword())){
            entity.setPassword(passwordEncoder.encode(userDTO.getNewPassword()));
            return userConverter.toDTO(userRepository.save(entity));
        } else {
            throw new Exception("Password not match");
        }

    }

    @Override
    public UserDTO changeUserAvatar(UserDTO userDTO) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(username == null){
            throw new Exception("User not login");
        }
        if(userDTO.getAvatar() == null){
            throw new Exception("New user avatar not to be empty");
        }
        UserEnity entity = userRepository.findByUsername(username);
        entity.setAvatar(userDTO.getAvatar());
        return userConverter.toDTO(userRepository.save(entity));
    }
}
