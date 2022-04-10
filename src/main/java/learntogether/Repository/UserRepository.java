package learntogether.Repository;

import learntogether.Entity.UserEnity;
import org.springframework.data.jpa.repository.JpaRepository;

/*
  Created by Luvbert
*/
public interface UserRepository extends JpaRepository<UserEnity, Long> {
    UserEnity findOneByUsernameAndStatus(String username, int status);
    UserEnity findByEmail(String email);
    UserEnity findByUsername(String username);
    UserEnity findUserById(Long id);
}
