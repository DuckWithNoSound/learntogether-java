package learntogether.Service;

import learntogether.Converter.CommentPostConverter;
import learntogether.DTO.CommentPostDTO;
import learntogether.Entity.CommentPostEntity;
import learntogether.Entity.UserEnity;
import learntogether.IService.ICommentPostService;
import learntogether.Repository.CommentPostRepository;
import learntogether.Repository.PostRepository;
import learntogether.Repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/*
  Created by Luvbert
*/
@Service
@Transactional
public class CommentPostService implements ICommentPostService {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private CommentPostConverter commentPostConverter;
    private CommentPostRepository commentPostRepository;

    public CommentPostService(PostRepository postRepository, UserRepository userRepository, CommentPostConverter commentPostConverter, CommentPostRepository commentPostRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentPostConverter = commentPostConverter;
        this.commentPostRepository = commentPostRepository;
    }

    @Override
    public CommentPostDTO createCommentPost(CommentPostDTO dto) throws Exception {
        CommentPostEntity entity = new CommentPostEntity();
        if(dto.getContent() == null) throw new Exception("Content is empty");
        if(dto.getPostId() == null) throw new Exception("No post to comment");

        entity.setContent(dto.getContent());
        entity.setPost(postRepository.findOne(dto.getPostId()));
        entity.setScore(0);
        entity.setCreatedDate(new Date());

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        entity.setUser(userRepository.findByUsername(username));

        return commentPostConverter.toDTO(commentPostRepository.save(entity));
    }

    @Override
    public CommentPostDTO updateCommentPost(CommentPostDTO commentPostDTO) throws Exception {
        return null;
    }

    @Override
    public CommentPostDTO deleteCommentPost(Long[] ids) throws Exception {
        return null;
    }
}
