package learntogether.API;

import learntogether.DTO.PostDTO;
import learntogether.IService.IPostService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
            message.put("Message", "Create failed: " + exception.getMessage());
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
            exception.printStackTrace();
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
    public ResponseEntity<?> getPosts(@RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "size", required = false) Integer size,
                                      @RequestParam(value = "sort", required = false) String sortBy,
                                      @RequestParam(value = "dir", required = false) String sortDirection){
        List<PostDTO> posts;
        Pageable paging;

        try {
            if(page == null) {
                page = 0;
            } else {
                page--;
            }
            if(size == null) size = 10;
            if(sortBy != null){
                Sort sort;
                if(sortDirection == null || !sortDirection.toUpperCase().equals(Sort.Direction.ASC.toString())){
                    sort = new Sort(Sort.Direction.DESC, sortBy);
                } else {
                    sort = new Sort(Sort.Direction.ASC, sortBy);
                }
                paging = new PageRequest(page, size, sort);
            } else {
                paging = new PageRequest(page, size);
            }

            posts = postService.findAll(paging);
        } catch (Exception exception){
            exception.printStackTrace();
            Map<String, String> message = new HashMap<>();
            message.put("Message", "Failed: " + exception.getMessage());
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.ok(posts);
    }

    @GetMapping(value = "/api/post/all/count")
    public ResponseEntity<?> getNumberOfPost(){
        Map<String, Long> response = new HashMap<>();
        try{
            response.put("totalPosts", postService.countAllPost());
            return ResponseEntity.ok(response);
        } catch (Exception exception) {
            exception.printStackTrace();
            Map<String, String> message = new HashMap<>();
            message.put("Message", "Failed: " + exception.getMessage());
            return ResponseEntity.ok(message);
        }
    }

    @GetMapping(value = "/api/post/score/currently")
    public ResponseEntity<?> getCurrentVote(@RequestParam(value = "postid") Long postId){
        Map<String, Byte> response = new HashMap<>();
        try{
            response.put("currentVote", postService.getCurrentScoreVote(postId));
            return ResponseEntity.ok(response);
        } catch (Exception exception) {
            exception.printStackTrace();
            Map<String, String> message = new HashMap<>();
            message.put("Message", "Failed: " + exception.getMessage());
            return ResponseEntity.ok(message);
        }
    }

    @GetMapping(value = "/api/post/score")
    public ResponseEntity<?> upOrDownScorePost(@RequestParam(value = "postid") Long postId, @RequestParam(value = "scoretype", required = false) Byte scoreType){
        Map<String, Integer> response = new HashMap<>();
        try{
            if(scoreType == null) scoreType = 1;
            response.put("score", postService.upOrDownScore(postId, scoreType));
            return ResponseEntity.ok(response);
        } catch (Exception exception) {
            exception.printStackTrace();
            Map<String, String> message = new HashMap<>();
            message.put("Message", "Failed: " + exception.getMessage());
            return ResponseEntity.ok(message);
        }
    }
    @GetMapping(value = "/api/post/view")
    public ResponseEntity<?> upViewPost(@RequestParam(value = "postid") Long postId){
        Map<String, Integer> response = new HashMap<>();
        try{
            response.put("score", postService.upView(postId));
            return ResponseEntity.ok(response);
        } catch (Exception exception) {
            exception.printStackTrace();
            Map<String, String> message = new HashMap<>();
            message.put("Message", "Failed: " + exception.getMessage());
            return ResponseEntity.ok(message);
        }
    }
}
