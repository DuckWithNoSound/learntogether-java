package learntogether.Repository;

import learntogether.Entity.UserEnity;
import org.springframework.data.jpa.repository.JpaRepository;

/*
  Created by Luvbert
*/
public interface UserRepository extends JpaRepository<UserEnity, Long> {
    public UserEnity findOneByUsernameAndStatus(String username, int status);
    public UserEnity findByEmail(String email);
    public UserEnity findByUsername(String username);
}
