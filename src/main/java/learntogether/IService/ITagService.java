package learntogether.IService;

import learntogether.Entity.TagEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/*
  Created by Luvbert
*/
@Service
public interface ITagService {
    List<TagEntity> findAll();
    TagEntity findByName(String tagName);
    TagEntity findBySlug(String tagSlug);
}
