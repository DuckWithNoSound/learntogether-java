package learntogether.IService;

import learntogether.DTO.PostDTO;
import org.springframework.stereotype.Service;

import java.util.Map;

/*
  Created by Luvbert
*/
@Service
public interface IPostService {
    PostDTO createNewPost(PostDTO postDTO);
    PostDTO findPostById(Long postID);
}
