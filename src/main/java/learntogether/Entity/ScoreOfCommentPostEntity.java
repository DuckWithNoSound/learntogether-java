package learntogether.Entity;

import javax.persistence.*;

/*
  Created by Luvbert
*/
@Entity
@Table(name = "score_of_comment_post")
public class ScoreOfCommentPostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "score_type", columnDefinition = "TINYINT")
    private Byte scoreType;

    @ManyToOne
    @JoinColumn(name = "comment_post_id")
    private CommentPostEntity commentPost;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEnity user;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Byte getScoreType() {
        return scoreType;
    }

    public void setScoreType(Byte scoreType) {
        this.scoreType = scoreType;
    }

    public CommentPostEntity getCommentPost() {
        return commentPost;
    }

    public void setCommentPost(CommentPostEntity commentPost) {
        this.commentPost = commentPost;
    }

    public UserEnity getUser() {
        return user;
    }

    public void setUser(UserEnity user) {
        this.user = user;
    }
}
