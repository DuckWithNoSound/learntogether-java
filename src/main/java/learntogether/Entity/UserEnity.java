package learntogether.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/*
  Created by Luvbert
*/
@Entity
@Table(name = "user")
public class UserEnity extends BaseEntity{

    @Column
    private String username;

    @Column
    private String password;

    @Column(columnDefinition = "TINYINT")
    private Integer status;

    @Column
    private String avatar;

    @Column
    private String fullname;

    @Column
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "user_quote")
    private String userQuote;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserQuote() {
        return userQuote;
    }

    public void setUserQuote(String userQuote) {
        this.userQuote = userQuote;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}
