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
    UserDTO findUserByUsername(String username);
    UserDTO findUserByUserId(Long userId);
    boolean isEmailExist(String email);
    boolean isUsernameExist(String username);
    boolean isUsernameAndPasswordMatch(String username, String password);
    UserDTO changeUserQuote(UserDTO userDTO) throws Exception;
    UserDTO changeUserPhoneOrFullname(UserDTO userDTO) throws Exception;
    UserDTO changeUserPassword(UserDTO userDTO) throws Exception;
    UserDTO changeUserAvatar(UserDTO userDTO) throws Exception;
}
