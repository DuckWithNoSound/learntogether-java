package learntogether.Service;

import learntogether.Converter.UserConverter;
import learntogether.DTO.UserDTO;
import learntogether.Entity.RoleEntity;
import learntogether.Entity.UserEnity;
import learntogether.IService.IUserService;
import learntogether.Repository.UserRepository;
import learntogether.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
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

    public UserService(UserRepository userRepository, UserConverter userConverter){
        this.userRepository = userRepository;
        this.userConverter = userConverter;
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
    public UserEnity findUserByUsername(String username) {
        return userRepository.findByUsername(username);
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
        UserEnity userEnity = findUserByUsername(username);
        if(userEnity == null) return false;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, userEnity.getPassword());
    }
}
