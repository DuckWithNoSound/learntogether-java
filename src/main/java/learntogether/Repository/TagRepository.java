package learntogether.Repository;

import learntogether.Entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
  Created by Luvbert
*/
public interface TagRepository extends JpaRepository<TagEntity, String> {
    TagEntity findByName(String tagName);
    TagEntity findBySlug(String tagSlug);
}
