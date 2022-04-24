package learntogether.Entity;

/*
  Created by Luvbert
*/

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment_post")
public class CommentPostEntity extends BaseEntity{

    @Column(name = "comment_content")
    private String content;

    @Column(name = "comment_score")
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEnity user;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    public UserEnity getUser() {
        return user;
    }

    public void setUser(UserEnity user) {
        this.user = user;
    }
}
