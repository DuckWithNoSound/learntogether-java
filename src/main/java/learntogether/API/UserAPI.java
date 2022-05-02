package learntogether.API;

import learntogether.DTO.UserDTO;
import learntogether.IService.IUserService;
import learntogether.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(path = "/api/user/quote")
    public ResponseEntity<?> changeQuote(@RequestBody UserDTO userDTO){
        UserDTO user;
        try{
            user = userService.changeUserQuote(userDTO);
            return ResponseEntity.ok(user);
        }catch (Exception exception){
            Map<String, String> message = new HashMap<>();
            message.put("Message", "Failed: " + exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.ok(message);
        }
    }

    @PutMapping(path = "/api/user/infor")
    public ResponseEntity<?> changeInfor(@RequestBody UserDTO userDTO){
        UserDTO user;
        try{
            user = userService.changeUserPhoneOrFullname(userDTO);
            return ResponseEntity.ok(user);
        }catch (Exception exception){
            Map<String, String> message = new HashMap<>();
            message.put("Message", "Failed: " + exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.ok(message);
        }
    }

    @PutMapping(path = "/api/user/password")
    public ResponseEntity<?> changePassword(@RequestBody UserDTO userDTO){
        UserDTO user;
        try{
            user = userService.changeUserPassword(userDTO);
            return ResponseEntity.ok(user);
        }catch (Exception exception){
            Map<String, String> message = new HashMap<>();
            message.put("Message", "Failed: " + exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.ok(message);
        }
    }

    @PutMapping(path = "/api/user/avatar")
    public ResponseEntity<?> changeAvatar(@RequestBody UserDTO userDTO){
        UserDTO user;
        try{
            user = userService.changeUserAvatar(userDTO);
            return ResponseEntity.ok(user);
        }catch (Exception exception){
            Map<String, String> message = new HashMap<>();
            message.put("Message", "Failed: " + exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.ok(message);
        }
    }
}
