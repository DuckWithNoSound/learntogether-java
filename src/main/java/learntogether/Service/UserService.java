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

    public UserService(UserRepository userRepository, UserConverter userConverter, PostRepository postRepository){
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.postRepository = postRepository;
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
}
