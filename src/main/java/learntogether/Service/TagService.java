package learntogether.Service;

import learntogether.Entity.TagEntity;
import learntogether.IService.ITagService;
import learntogether.Repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
  Created by Luvbert
*/
@Service
public class TagService implements ITagService {

    TagRepository tagRepository;

    public TagService(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    @Override
    public List<TagEntity> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public TagEntity findByName(String tagName) {
        return tagRepository.findByName(tagName);
    }

    @Override
    public TagEntity findBySlug(String tagSlug) {
        return tagRepository.findBySlug(tagSlug);
    }
}
