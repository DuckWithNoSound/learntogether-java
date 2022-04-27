package learntogether.API;

import learntogether.DTO.TagDTO;
import learntogether.IService.ITagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
  Created by Luvbert
*/
@RestController
public class TagAPI {
    private ITagService tagService;

    public TagAPI(ITagService tagService){
        this.tagService = tagService;
    }

    @GetMapping(value = "/api/tag/all")
    public ResponseEntity getAllTags(){
        List<TagDTO> listTag;
        try{
            listTag = tagService.findAll();
            listTag.size();
        } catch (Exception exception){
            Map<String, String> message = new HashMap<>();
            message.put("Message", "Failed: " + exception.getMessage());
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.ok(listTag);
    }
}
