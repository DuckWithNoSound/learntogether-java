package learntogether.IService;

import learntogether.DTO.PostDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/*
  Created by Luvbert
*/
@Service
public interface IPostService {
    PostDTO createNewPost(PostDTO postDTO) throws Exception;
    PostDTO findPostById(Long postID) throws Exception;
    PostDTO updatePost(PostDTO postDTO) throws Exception;
    boolean deletePost(Long[] arrPostId) throws Exception;
    List<PostDTO> findAll(Pageable pageRequest) throws Exception;
    List<PostDTO> findAllByKeyword(String keyword) throws Exception;
    Long countAllPost() throws Exception;
    Integer upOrDownScore(Long postId, Byte scoreType) throws Exception;
    Integer upView(Long postId) throws Exception;
    Byte getCurrentScoreVote(Long postId) throws Exception;
}
