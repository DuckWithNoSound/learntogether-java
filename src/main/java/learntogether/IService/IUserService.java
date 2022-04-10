package learntogether.IService;

import learntogether.DTO.UserDTO;
import learntogether.Entity.UserEnity;
import org.springframework.stereotype.Service;

import java.util.Map;

/*
  Created by Luvbert
*/
@Service
public interface IUserService {
    Map registerNewUserAccount(UserDTO userDTO);
    UserEnity findUserByUsername(String username);
    boolean isEmailExist(String email);
    boolean isUsernameExist(String username);
    boolean isUsernameAndPasswordMatch(String username, String password);
}
