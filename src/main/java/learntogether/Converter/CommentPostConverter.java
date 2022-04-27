package learntogether.Converter;

import learntogether.DTO.CommentPostDTO;
import learntogether.Entity.CommentPostEntity;
import learntogether.IService.IPostService;
import learntogether.IService.IUserService;
import learntogether.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

/*
  Created by Luvbert
*/
@Component
@Scope("prototype")
public class CommentPostConverter implements IConverter<CommentPostDTO, CommentPostEntity> {

    @Override
    public CommentPostDTO toDTO(CommentPostEntity entity){
        CommentPostDTO dto = new CommentPostDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setScore(entity.getScore());
        dto.setPostId(entity.getPost().getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setAuthorName(entity.getUser().getUsername());
        dto.setAuthorAvatar(entity.getUser().getAvatar());
        dto.setAuthorRole(entity.getUser().getRole().getRoleName());
        return dto;
    }

    @Override
    public CommentPostEntity toEntity(CommentPostDTO dto) {
        return null;
    }

    /*
    public CommentPostEntity toEntity(CommentPostDTO dto) throws Exception {
        CommentPostEntity entity = new CommentPostEntity();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setScore(dto.getScore());
        entity.setPost(postConverter.toEntity(postService.findPostById(dto.getPostId())));
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setUser(userService.findUserByUsername(dto.getAuthorName()));
        return entity;
    }
     */
}
