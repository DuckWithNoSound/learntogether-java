package learntogether.API;

import learntogether.DTO.CommentPostDTO;
import learntogether.IService.ICommentPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/*
  Created by Luvbert
*/
@RestController
public class CommentPostAPI {

    private ICommentPostService commentPostService;

    public CommentPostAPI(ICommentPostService commentPostService){
        this.commentPostService = commentPostService;
    }

    @PostMapping(value = "/api/commentpost/")
    public ResponseEntity<?> createCommentPost(@RequestBody CommentPostDTO commentPostDTO){
        CommentPostDTO savedComment;
        try{
            savedComment = commentPostService.createCommentPost(commentPostDTO);
        } catch (Exception exception){
            Map<String, String> message = new HashMap<>();
            message.put("Message", "Create failed: " + exception.getMessage());
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.ok(savedComment);
    }

    @PutMapping(value = "/api/commentpost/")
    public ResponseEntity<?> updateCommentPost(@RequestBody CommentPostDTO commentPostDTO){
        CommentPostDTO savedComment;
        try{
            savedComment = commentPostService.updateCommentPost(commentPostDTO);
        } catch (Exception exception){
            Map<String, String> message = new HashMap<>();
            message.put("Message", "Update failed: " + exception.getMessage());
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.ok(savedComment);
    }

    @DeleteMapping(value = "/api/commentpost/")
    public ResponseEntity<?> deleteCommentPost(@RequestBody CommentPostDTO commentPostDTO){
        Map<String, String> message = new HashMap<>();
        try {
            commentPostService.deleteCommentPost(commentPostDTO.getIds());
        } catch (Exception ex){
            message.put("Message", "Delete failed: " + ex.getMessage());
        }
        if(message.isEmpty()){
            message.put("Message", "Delete success !");
        }
        return ResponseEntity.ok(message);
    }
}
