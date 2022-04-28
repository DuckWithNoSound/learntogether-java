package learntogether.Repository;

import learntogether.Entity.CommentPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
  Created by Luvbert
*/
@Repository
public interface CommentPostRepository extends JpaRepository<CommentPostEntity, Long> {
}
