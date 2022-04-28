package learntogether.Entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
  Created by Luvbert
*/
@Entity
@Table(name = "post")
public class PostEntity extends BaseEntity{

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "view_number")
    private Integer viewNumber;

    @Column(name = "score")
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEnity user;

    @OneToMany(mappedBy = "post")
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @OrderBy("score desc")
    List<CommentPostEntity> commentsPost = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "post_tag_mtm", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<TagEntity> tags = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Integer viewNumber) {
        this.viewNumber = viewNumber;
    }

    public List<TagEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagEntity> tags) {
        this.tags = tags;
    }

    public UserEnity getUser() {
        return user;
    }

    public void setUser(UserEnity user) {
        this.user = user;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<CommentPostEntity> getCommentsPost() {
        return commentsPost;
    }

    public void setCommentsPost(List<CommentPostEntity> commentsPost) {
        this.commentsPost = commentsPost;
    }
}
