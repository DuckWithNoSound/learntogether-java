package learntogether.DTO;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/*
  Created by Luvbert
*/
public class LoginDTO {
    @NotNull
    @NotEmpty
    @Size(min = 6, max = 255)
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 255)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginDTO() {
    }

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
