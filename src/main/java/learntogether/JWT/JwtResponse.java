package learntogether.JWT;

/*
  Created by Luvbert
*/
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
    private String username;
    private String fullname;
    private String avatar;
    private String userQuote;
    private String roleName;

    public JwtResponse(String accessToken, String refreshToken, String username, String fullname, String avatar, String userQuote, String roleName) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.username = username;
        this.fullname = fullname;
        this.avatar = avatar;
        this.userQuote = userQuote;
        this.roleName = roleName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserQuote() {
        return userQuote;
    }

    public void setUserQuote(String userQuote) {
        this.userQuote = userQuote;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
