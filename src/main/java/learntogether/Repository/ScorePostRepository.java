package learntogether.Repository;

import learntogether.Entity.ScorePostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/*
  Created by Luvbert
*/
public interface ScorePostRepository extends JpaRepository<ScorePostEntity, Long> {
}
