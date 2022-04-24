package learntogether.Service;

import learntogether.Converter.PostConverter;
import learntogether.DTO.PostDTO;
import learntogether.DTO.UserDetail;
import learntogether.Entity.PostEntity;
import learntogether.Entity.ScorePostEntity;
import learntogether.Entity.TagEntity;
import learntogether.IService.IPostService;
import learntogether.IService.ITagService;
import learntogether.IService.IUserService;
import learntogether.Repository.PostRepository;
import learntogether.Repository.ScorePostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/*
  Created by Luvbert
*/
@Service
@Transactional
public class PostService implements IPostService {

    private PostRepository postRepository;
    private ITagService tagService;
    private IUserService userService;
    private PostConverter postConverter;
    private ScorePostRepository scorePostRepository;

    public PostService(PostRepository postRepository, ITagService tagService, IUserService userService, PostConverter postConverter, ScorePostRepository scorePostRepository){
        this.postRepository = postRepository;
        this.tagService = tagService;
        this.userService = userService;
        this.postConverter = postConverter;
        this.scorePostRepository = scorePostRepository;
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
        PostEntity postEntity = postRepository.getOne(postDTO.getId());
        if(postEntity == null){
            throw new Exception("Source post not exist !");
        }
        if(postDTO.getTitle() != null) postEntity.setTitle(postDTO.getTitle());
        if(postDTO.getContent() != null) postEntity.setContent(postDTO.getContent());
        postEntity.setModifiedDate(new Date());
        // convert tag slug to tag entity
        List<TagEntity> tags = new ArrayList<>();
        for(String tagSlug : postDTO.getListTagSlug()){
            tags.add(tagService.findBySlug(tagSlug));
        }
        postEntity.setTags(tags);
        postEntity.setUser(userService.findUserByUsername(username));
        // get some hide information back to DTO
        postDTO.setId(postRepository.save(postEntity).getId());
        return postDTO;
    }

    @Override
    public boolean deletePost (Long[] arrPostId) throws Exception {
        String username = ((UserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        if(username == null){
            throw new Exception("User not login !");
        }
        if(arrPostId == null) {
            throw new Exception("Nothing to delete !");
        }
        for (int i = 0; i < arrPostId.length; i++) {
            postRepository.delete(postRepository.getOne(arrPostId[i]));
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
    public Integer upOrDownScore(Long postId, Byte scoreType) throws Exception {
        ScorePostEntity scorePostEntity = new ScorePostEntity();
        scorePostEntity.setScoreType(scoreType);
        scorePostEntity.setPost(postRepository.findOne(postId));
        scorePostRepository.save(scorePostEntity);
        return postRepository.findOne(postId).getScore();
    }
}
