package learntogether.Service;

import learntogether.Converter.TagConverter;
import learntogether.DTO.TagDTO;
import learntogether.Entity.TagEntity;
import learntogether.IService.ITagService;
import learntogether.Repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
  Created by Luvbert
*/
@Service
public class TagService implements ITagService {

    private TagRepository tagRepository;
    private TagConverter tagConverter;

    public TagService(TagRepository tagRepository, TagConverter tagConverter){
        this.tagRepository = tagRepository;
        this.tagConverter = tagConverter;
    }

    @Override
    public List<TagDTO> findAll() throws Exception {
        List<TagEntity> entities = tagRepository.findAll();
        List<TagDTO> dtos = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            dtos.add(tagConverter.toDTO(entities.get(i)));
        }

        return dtos;
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
