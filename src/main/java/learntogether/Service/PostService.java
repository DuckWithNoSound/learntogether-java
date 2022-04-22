package learntogether.Service;

import learntogether.DTO.PostDTO;
import learntogether.DTO.UserDetail;
import learntogether.Entity.PostEntity;
import learntogether.Entity.TagEntity;
import learntogether.IService.IPostService;
import learntogether.IService.ITagService;
import learntogether.IService.IUserService;
import learntogether.Repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public PostService(PostRepository postRepository, ITagService tagService, IUserService userService){
        this.postRepository = postRepository;
        this.tagService = tagService;
        this.userService = userService;
    }

    @Override
    public PostDTO createNewPost(PostDTO postDTO) throws IllegalArgumentException, Exception {
        String username = ((UserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        if(username == null){
            throw new Exception("User not login !");
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
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postDTO.getTitle());
        postEntity.setContent(postDTO.getContent());
        postEntity.setViewNumber(0);
        postEntity.setCreatedDate(new Date());
        //tags
        List<TagEntity> tags = new ArrayList<>();
        for(String tagSlug : postDTO.getListTagSlug()){
            tags.add(tagService.findBySlug(tagSlug));
        }
        postEntity.setTags(tags);
        //
        postEntity.setUser(userService.findUserByUsername(username));
        // get some hide information back to DTO
        postDTO.setId(postRepository.save(postEntity).getId());
        return postDTO;
    }

    @Override
    public PostDTO findPostById(Long postID) throws IllegalArgumentException, Exception {
        PostEntity postEntity = postRepository.findOne(postID);
        if(postEntity == null){
            throw new Exception("Post not found !");
        }
        PostDTO postDTO = new PostDTO();
        postDTO.setId(postEntity.getId());
        postDTO.setTitle(postEntity.getTitle());
        postDTO.setContent(postEntity.getContent());
        postDTO.setAuthorName(postEntity.getUser().getUsername());
        postDTO.setCreatedDate(postEntity.getCreatedDate());
        postDTO.setModifiedDate(postEntity.getModifiedDate());
        // get all tag slugs
        List<TagEntity> tags = postEntity.getTags();
        List<String> listTagSlug = new ArrayList<>();
        for (int i = 0; i < tags.size(); i++) {
            listTagSlug.add(tags.get(i).getSlug());
        }
        //
        postDTO.setListTagSlug(listTagSlug);

        return postDTO;
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
            postRepository.delete((long)arrPostId[i]);
        }
        return true;
    }

    @Override
    public Page<PostDTO> findAll() throws Exception {
        Page<PostEntity> page = postRepository.findAll(PageRequest.of(0, 10));
        return null;
    }
}
