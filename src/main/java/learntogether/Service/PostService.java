package learntogether.Service;

import learntogether.DTO.PostDTO;
import learntogether.Entity.PostEntity;
import learntogether.Entity.TagEntity;
import learntogether.IService.IPostService;
import learntogether.IService.ITagService;
import learntogether.IService.IUserService;
import learntogether.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public PostDTO createNewPost(PostDTO postDTO) {
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
        postEntity.setUser(userService.findUserByUsername(postDTO.getAuthorName()));
        // get some hide information back to DTO
        postDTO.setId(postRepository.save(postEntity).getId());
        return postDTO;
    }

    @Override
    public PostDTO findPostById(Long postID) {
        return null;
    }
}
