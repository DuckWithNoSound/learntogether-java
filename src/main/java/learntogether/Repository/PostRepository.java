package learntogether.Repository;

import learntogether.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/*
  Created by Luvbert
*/
public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
