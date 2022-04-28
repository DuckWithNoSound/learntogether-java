package learntogether.Repository;

import learntogether.Entity.PostEntity;
import learntogether.Entity.ScorePostEntity;
import learntogether.Entity.UserEnity;
import org.springframework.data.jpa.repository.JpaRepository;

/*
  Created by Luvbert
*/
public interface ScorePostRepository extends JpaRepository<ScorePostEntity, Long> {
    Integer countByPostIdAndUserId(Long postId, Long userId);
    Integer countAllByPost(PostEntity postEntity);
    ScorePostEntity findByPostIdAndUserId(Long postId, Long userId);
    void deleteAllByPost(PostEntity postEntity);
}
