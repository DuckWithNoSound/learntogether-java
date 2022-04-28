package learntogether.Repository;

import learntogether.Entity.PostEntity;
import learntogether.Entity.UserEnity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
  Created by Luvbert
*/
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findAllByUser(UserEnity user);
    Integer countAllByUser(UserEnity user);
}
