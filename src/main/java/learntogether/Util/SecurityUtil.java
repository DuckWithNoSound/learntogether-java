package learntogether.Util;

import learntogether.DTO.UserDetail;
import org.springframework.security.core.context.SecurityContextHolder;

/*
  Created by Luvbert
*/
public class SecurityUtil {
    public static UserDetail getPrincipal(){
        return (UserDetail) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
