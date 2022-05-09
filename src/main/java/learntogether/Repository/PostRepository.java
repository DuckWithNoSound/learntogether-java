package learntogether.Repository;

import learntogether.Entity.PostEntity;
import learntogether.Entity.UserEnity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

/*
  Created by Luvbert
*/
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findAllByUser(UserEnity user);
    List<PostEntity> findAllByTitleContainingOrContentContaining(String title, String content);
    Integer countAllByUser(UserEnity user);
}
