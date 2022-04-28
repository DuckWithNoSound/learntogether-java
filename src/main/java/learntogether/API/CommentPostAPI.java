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

    @GetMapping(value = "/api/commentpost/score/currently")
    public ResponseEntity<?> getCurrentVote(@RequestParam(value = "commentpostid") Long commentPostId){
        Map<String, Byte> response = new HashMap<>();
        try{
            response.put("currentVote", commentPostService.getCurrentScoreVote(commentPostId));
            return ResponseEntity.ok(response);
        } catch (Exception exception) {
            exception.printStackTrace();
            Map<String, String> message = new HashMap<>();
            message.put("Message", "Failed: " + exception.getMessage());
            return ResponseEntity.ok(message);
        }
    }

    @GetMapping(value = "/api/commentpost/score")
    public ResponseEntity<?> upDownScoreCommentPost(@RequestParam(value = "commentpostid")Long commentPostId, @RequestParam(value = "scoretype")Byte scoreType){
        Map<String, Integer> response = new HashMap<>();
        try {
            if(scoreType == null || scoreType != -1) scoreType = 1;
            response.put("score", commentPostService.upOrDownScore(commentPostId, scoreType));
            return ResponseEntity.ok(response);
        } catch (Exception exception){
            Map<String, String> message = new HashMap<>();
            message.put("Message", "Vote failed: " + exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.ok(message);
        }
    }
}
