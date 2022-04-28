package learntogether.Repository;

import learntogether.Entity.CommentPostEntity;
import learntogether.Entity.ScoreOfCommentPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
  Created by Luvbert
*/
@Repository
public interface ScoreOfCommentPostRepository extends JpaRepository<ScoreOfCommentPostEntity, Long> {
    ScoreOfCommentPostEntity findByCommentPostIdAndUserId(Long commentPostId, Long UserId);
    void deleteAllByCommentPost(CommentPostEntity commentPostEntity);
}
