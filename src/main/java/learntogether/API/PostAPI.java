package learntogether.API;

import learntogether.DTO.PostDTO;
import learntogether.IService.IPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/*
  Created by Luvbert
*/
@RestController
public class PostAPI {

    private IPostService postService;

    public PostAPI(IPostService postService){
        this.postService = postService;
    }

    @GetMapping(value = "/api/post/{postId}")
    public ResponseEntity<?> getPost(@PathVariable(name = "postId") Long postId){
        PostDTO foundPost;
        try{
            foundPost = postService.findPostById(postId);
        } catch (Exception exception){
            Map<String, String> message = new HashMap<>();
            message.put("Message", "Not found: " + exception.getMessage());
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.ok(foundPost);
    }

    @PostMapping(value = "/api/post")
    public ResponseEntity<?> createPost(@RequestBody PostDTO postDTO){
        PostDTO savedPost;
        try{
            savedPost = postService.createNewPost(postDTO);
        } catch (Exception exception){
            Map<String, String> message = new HashMap<>();
            message.put("Message", "Update failed: " + exception.getMessage());
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.ok(savedPost);
    }

    @PutMapping(value = "/api/post")
    public ResponseEntity<?> updatePost(@RequestBody PostDTO postDTO){
        PostDTO savedPost;
        try{
            savedPost = postService.updatePost(postDTO);
        } catch (Exception exception){
            Map<String, String> message = new HashMap<>();
            message.put("Message", "Update failed: " + exception.getMessage());
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.ok(savedPost);
    }

    @DeleteMapping(value = "/api/post")
    public ResponseEntity<?> deletePost(@RequestBody PostDTO postDTO){
        Map<String, String> message = new HashMap<>();
        try {
            postService.deletePost(postDTO.getIds());
        } catch (Exception ex){
            message.put("Message", "Delete failed: " + ex.getMessage());
        }
        if(message.isEmpty()){
            message.put("Message", "Delete success !");
        }
        return ResponseEntity.ok(message);
    }

    @GetMapping(value = "/api/post/all")
    public ResponseEntity<?> getPosts(){
        try {
            postService.findAll();
        } catch (Exception exception){

        }
        return ResponseEntity.ok().build();
    }
}
