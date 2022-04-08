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

}
