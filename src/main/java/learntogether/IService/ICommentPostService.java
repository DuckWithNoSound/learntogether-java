package learntogether.IService;

import learntogether.DTO.CommentPostDTO;
import org.springframework.stereotype.Service;

/*
  Created by Luvbert
*/
@Service
public interface ICommentPostService {
    CommentPostDTO createCommentPost(CommentPostDTO commentPostDTO) throws Exception;
    CommentPostDTO updateCommentPost(CommentPostDTO commentPostDTO) throws Exception;
    CommentPostDTO deleteCommentPost(Long[] ids) throws Exception;
}