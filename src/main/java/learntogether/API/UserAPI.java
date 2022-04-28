package learntogether.API;

import learntogether.DTO.UserDTO;
import learntogether.IService.IUserService;
import learntogether.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/*
  Created by Luvbert
*/
@RestController
public class UserAPI {

    private IUserService userService;

    public UserAPI(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/api/user")
    public ResponseEntity<?> getUser(@RequestParam(value = "username", required = false) String username,
                                     @RequestParam(value = "userid", required = false) Long userId){
        try{
            if (userId != null){
                UserDTO user = userService.findUserByUserId(userId);
                return ResponseEntity.ok(user);
            }
            if(username != null) {
                UserDTO user = userService.findUserByUsername(username);
                return ResponseEntity.ok(user);
            }
            throw new Exception("No user's information to find");
        }catch (Exception exception){
            Map<String, String> message = new HashMap<>();
            message.put("Message", "Failed: " + exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.ok(message);
        }
    }
}
