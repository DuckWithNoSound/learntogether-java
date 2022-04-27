package learntogether.Converter;

import learntogether.DTO.TagDTO;
import learntogether.Entity.TagEntity;
import org.springframework.stereotype.Component;

/*
  Created by Luvbert
*/
@Component
public class TagConverter implements IConverter<TagDTO, TagEntity> {
    @Override
    public TagDTO toDTO(TagEntity entity) {
        TagDTO dto = new TagDTO();

        dto.setId(entity.getId());
        dto.setTagName(entity.getName());
        dto.setTagSlug(entity.getSlug());

        return dto;
    }

    @Override
    public TagEntity toEntity(TagDTO dto) {
        TagEntity entity = new TagEntity();

        entity.setId(dto.getId());
        entity.setName(dto.getTagName());
        entity.setSlug(dto.getTagSlug());

        return null;
    }
}
