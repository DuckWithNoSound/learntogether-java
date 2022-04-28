package learntogether.Service;

import learntogether.Converter.CommentPostConverter;
import learntogether.DTO.CommentPostDTO;
import learntogether.DTO.UserDetail;
import learntogether.Entity.*;
import learntogether.IService.ICommentPostService;
import learntogether.Repository.CommentPostRepository;
import learntogether.Repository.PostRepository;
import learntogether.Repository.ScoreOfCommentPostRepository;
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
    private ScoreOfCommentPostRepository scoreOfCommentPostRepository;

    public CommentPostService(PostRepository postRepository, UserRepository userRepository,
                              CommentPostConverter commentPostConverter, CommentPostRepository commentPostRepository,
                              ScoreOfCommentPostRepository scoreOfCommentPostRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentPostConverter = commentPostConverter;
        this.commentPostRepository = commentPostRepository;
        this.scoreOfCommentPostRepository = scoreOfCommentPostRepository;
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

    @Override
    public Byte getCurrentScoreVote(Long commentPostId) throws Exception {
        UserDetail user = ((UserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ScoreOfCommentPostEntity entity = scoreOfCommentPostRepository.findByCommentPostIdAndUserId(commentPostId, user.getId());
        if(entity == null){
            return 0;
        } else {
            return entity.getScoreType();
        }
    }

    @Override
    public Integer upOrDownScore(Long commentPostId, Byte scoreType) throws Exception {
        CommentPostEntity commentPostEntity = commentPostRepository.findOne(commentPostId);
        UserDetail user = ((UserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ScoreOfCommentPostEntity scoreOfCommentPostEntity = scoreOfCommentPostRepository.findByCommentPostIdAndUserId(commentPostId, user.getId());
        if(scoreOfCommentPostEntity != null){
            if(scoreOfCommentPostEntity.getScoreType() == 1 && scoreType == -1){
                scoreOfCommentPostEntity.setScoreType((byte) -1);
                scoreOfCommentPostRepository.save(scoreOfCommentPostEntity);
                return (commentPostEntity.getScore()-2);
            } else if (scoreOfCommentPostEntity.getScoreType() == -1 && scoreType == 1){
                scoreOfCommentPostEntity.setScoreType((byte) 1);
                scoreOfCommentPostRepository.save(scoreOfCommentPostEntity);
                return (commentPostEntity.getScore()+2);
            } else {
                return commentPostEntity.getScore();
            }
        } else {
            scoreOfCommentPostEntity = new ScoreOfCommentPostEntity();
            scoreOfCommentPostEntity.setScoreType(scoreType);
            scoreOfCommentPostEntity.setCommentPost(commentPostEntity);
            scoreOfCommentPostEntity.setUser(userRepository.findByUsername(user.getUsername()));
            scoreOfCommentPostRepository.save(scoreOfCommentPostEntity);
            if(scoreType == 1){
                return (commentPostEntity.getScore()+1);
            } else {
                return (commentPostEntity.getScore()-1);
            }
        }
    }
}
