package learntogether.Util;

import learntogether.DTO.MyUser;
import org.springframework.security.core.context.SecurityContextHolder;

/*
  Created by Luvbert
*/
public class SecurityUtil {
    public static MyUser getPrincipal(){
        MyUser myUser = (MyUser) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return myUser;
    }
}
