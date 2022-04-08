package learntogether.Service;

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

/*
  Created by Luvbert
*/
@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Map registerNewUserAccount(UserDTO userDTO) {
        Map<String, String> message = new HashMap<String, String>();
        // Service validate
        if(isEmailExist(userDTO.getEmail())){
            message.put("email", "Email address is already registered !");
        }
        if(isUsernameExist(userDTO.getUsername())){
            message.put("username", "Username is already registered !");
        }
        //
        if(message.isEmpty()){
            UserEnity userEnity = new UserEnity();
            userEnity.setEmail(userDTO.getEmail());
            userEnity.setUsername(userDTO.getUsername());
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            userEnity.setPassword(encoder.encode(userDTO.getPassword()));
            userEnity.setAvatar("/assets/Uploads/Avatar/unset-icon.png");
            userEnity.setFullname("Chưa có");
            userEnity.setPhoneNumber("Chưa có");
            userEnity.setUserQuote("Học tập cùng LearnTogether");
            userEnity.setStatus(1);
            RoleEntity role = roleRepository.findById(5l);
            userEnity.setRole(role);
            userRepository.save(userEnity);
        }
        return message;
    }

    public boolean isEmailExist(String email){
        return userRepository.findByEmail(email) != null;
    }
    public boolean isUsernameExist(String username){
        return userRepository.findByUsername(username) != null;
    }
}
