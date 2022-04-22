package learntogether.IService;

import javafx.geometry.Pos;
import learntogether.DTO.PostDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/*
  Created by Luvbert
*/
@Service
public interface IPostService {
    PostDTO createNewPost(PostDTO postDTO) throws Exception;
    PostDTO findPostById(Long postID) throws Exception;
    PostDTO updatePost(PostDTO postDTO) throws Exception;
    boolean deletePost(Long[] arrPostId) throws Exception;
    Page<PostDTO> findAll() throws Exception;
}
