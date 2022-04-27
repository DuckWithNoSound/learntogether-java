package learntogether.Repository;

import learntogether.Entity.CommentPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/*
  Created by Luvbert
*/
public interface CommentPostRepository extends JpaRepository<CommentPostEntity, Long> {
}
