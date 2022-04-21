package learntogether.API;

import learntogether.DTO.PostDTO;
import learntogether.IService.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
  Created by Luvbert
*/
@RestController
public class PostAPI {

    private IPostService postService;

    public PostAPI(IPostService postService){
        this.postService = postService;
    }

    @PostMapping(value = "/api/post")
    public PostDTO createPost(@RequestBody @Valid PostDTO postDTO){

        return postService.createNewPost(postDTO);
    }
}
