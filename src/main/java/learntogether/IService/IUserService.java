package learntogether.IService;

import learntogether.DTO.UserDTO;
import org.springframework.stereotype.Service;

import java.util.Map;

/*
  Created by Luvbert
*/
@Service
public interface IUserService {
    public Map registerNewUserAccount(UserDTO userDTO);
}
