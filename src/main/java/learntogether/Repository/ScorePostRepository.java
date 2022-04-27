package learntogether.Repository;

import learntogether.Entity.ScorePostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/*
  Created by Luvbert
*/
public interface ScorePostRepository extends JpaRepository<ScorePostEntity, Long> {
    Integer countByPostIdAndUserId(Long postId, Long userId);
    ScorePostEntity findByPostIdAndUserId(Long postId, Long userId);
}
