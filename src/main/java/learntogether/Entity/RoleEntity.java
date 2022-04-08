package learntogether.Entity;

/*
  Created by Luvbert
*/

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity{

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<UserEnity> users = new ArrayList<>();

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<UserEnity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEnity> users) {
        this.users = users;
    }
}
