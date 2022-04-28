package learntogether.Service;

import learntogether.Converter.PostConverter;
import learntogether.DTO.PostDTO;
import learntogether.DTO.UserDetail;
import learntogether.Entity.CommentPostEntity;
import learntogether.Entity.PostEntity;
import learntogether.Entity.ScorePostEntity;
import learntogether.Entity.TagEntity;
import learntogether.IService.IPostService;
import learntogether.IService.ITagService;
import learntogether.Repository.PostRepository;
import learntogether.Repository.ScoreOfCommentPostRepository;
import learntogether.Repository.ScorePostRepository;
import learntogether.Repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
  Created by Luvbert
*/
@Service
@Transactional
public class PostService implements IPostService {

    private PostRepository postRepository;
    private ITagService tagService;
    private UserRepository userRepository;
    private PostConverter postConverter;
    private ScorePostRepository scorePostRepository;
    private ScoreOfCommentPostRepository scoreOfCommentPostRepository;

    public PostService(PostRepository postRepository, ITagService tagService, UserRepository userRepository,
                       PostConverter postConverter, ScorePostRepository scorePostRepository,
                       ScoreOfCommentPostRepository scoreOfCommentPostRepository){
        this.postRepository = postRepository;
        this.tagService = tagService;
        this.userRepository = userRepository;
        this.postConverter = postConverter;
        this.scorePostRepository = scorePostRepository;
        this.scoreOfCommentPostRepository = scoreOfCommentPostRepository;
    }

    @Override
    public PostDTO createNewPost(PostDTO postDTO) throws IllegalArgumentException, Exception {
        String username = ((UserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        if(username == null){
            throw new Exception("User not login !");
        } else {
            postDTO.setAuthorName(username);
        }
        if(postDTO.getTitle() == null || postDTO.getTitle().isEmpty()){
            throw new Exception("Title is empty !");
        }
        if(postDTO.getContent() == null || postDTO.getContent().isEmpty()){
            throw new Exception("Content is empty !");
        }
        if(postDTO.getListTagSlug() == null || postDTO.getListTagSlug().isEmpty()){
            throw new Exception("Tag is empty !");
        }

        // save and get some hide information back to DTO
        postDTO.setId(postRepository.save(postConverter.toEntity(postDTO)).getId());
        return postDTO;
    }

    @Override
    public PostDTO findPostById(Long postID) throws IllegalArgumentException, Exception {
        PostEntity postEntity = postRepository.getOne(postID);
        if(postEntity == null){
            throw new Exception("Post not found !");
        }

        return postConverter.toDTO(postEntity);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO) throws IllegalArgumentException, Exception{
        String username = ((UserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        if(username == null){
            throw new Exception("User not login !");
        }
        PostEntity postEntity = postRepository.findOne(postDTO.getId());
        if(postEntity == null){
            throw new Exception("Source post not exist !");
        }
        if(postDTO.getTitle() != null) postEntity.setTitle(postDTO.getTitle());
        if(postDTO.getContent() != null) postEntity.setContent(postDTO.getContent());
        postEntity.setModifiedDate(new Date());
        // convert tag slug to tag entity
        if(postDTO.getListTagSlug() != null){
            List<TagEntity> tags = new ArrayList<>();
            for(String tagSlug : postDTO.getListTagSlug()){
                tags.add(tagService.findBySlug(tagSlug));
            }
            postEntity.setTags(tags);
        }

        postEntity.setUser(userRepository.findByUsername(username));
        postRepository.save(postEntity);
        return postConverter.toDTO(postRepository.findOne(postEntity.getId()));
    }

    @Override
    public boolean deletePost (Long[] arrPostId) throws Exception {
        UserDetail user = ((UserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if(user == null){
            throw new Exception("User not login !");
        }
        if(arrPostId == null) {
            throw new Exception("Nothing to delete !");
        }
        for (int i = 0; i < arrPostId.length; i++) {
            PostEntity temp = postRepository.findOne(arrPostId[i]);
            if(temp.getUser().getUsername().equals(user.getUsername()) || user.getRole().getId() >= 3){
                scorePostRepository.deleteAllByPost(temp);
                List<CommentPostEntity> commentPostEntities = temp.getCommentsPost();
                for(int j = 0; j < commentPostEntities.size(); j++){
                    scoreOfCommentPostRepository.deleteAllByCommentPost(commentPostEntities.get(j));
                }
                postRepository.delete(temp);
            } else throw new Exception("You do not have permission to delete post(post id = " + temp.getId() + ").");
        }
        return true;
    }

    @Override
    public List<PostDTO> findAll(Pageable pageRequest) throws Exception {

        List<PostEntity> entities = postRepository.findAll(pageRequest).getContent();
        List<PostDTO> dtos = new ArrayList<>();
        for(PostEntity postEntity: entities){
            dtos.add(postConverter.toDTO(postEntity));
        }
        return dtos;
    }

    @Override
    public Long countAllPost() throws Exception {
        return postRepository.count();
    }

    @Override
    public Byte getCurrentScoreVote(Long postId) throws Exception {
        UserDetail user = ((UserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ScorePostEntity scorePostEntity = scorePostRepository.findByPostIdAndUserId(postId, user.getId());
        if(scorePostEntity == null){
            return 0;
        } else {
            return scorePostEntity.getScoreType();
        }
    }

    @Override
    public Integer upOrDownScore(Long postId, Byte scoreType) throws Exception {
        PostEntity postEntity = postRepository.findOne(postId);
        UserDetail user = ((UserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ScorePostEntity scorePostEntity = scorePostRepository.findByPostIdAndUserId(postId, user.getId());
        if(scorePostEntity != null){
            if(scorePostEntity.getScoreType() == 1 && scoreType == -1){
                scorePostEntity.setScoreType((byte) -1);
                scorePostRepository.save(scorePostEntity);
                return (postEntity.getScore()-2);
            } else if (scorePostEntity.getScoreType() == -1 && scoreType == 1){
                scorePostEntity.setScoreType((byte) 1);
                scorePostRepository.save(scorePostEntity);
                return (postEntity.getScore()+2);
            } else {
                return postEntity.getScore();
            }
        } else {
            scorePostEntity = new ScorePostEntity();
            scorePostEntity.setScoreType(scoreType);
            scorePostEntity.setPost(postEntity);
            scorePostEntity.setUser(userRepository.findByUsername(user.getUsername()));
            scorePostRepository.save(scorePostEntity);
            if(scoreType == 1){
                return (postEntity.getScore()+1);
            } else {
                return (postEntity.getScore()-1);
            }
        }
    }

    @Override
    public Integer upView(Long postId) throws Exception {
        PostEntity postEntity = postRepository.findOne(postId);
        Integer viewNumber = postEntity.getViewNumber() + 1;
        postEntity.setViewNumber(viewNumber);
        return postRepository.save(postEntity).getScore();
    }
}
