package learntogether.Entity;

/*
  Created by Luvbert
*/

import javax.persistence.*;

@Entity
@Table(name = "score")
public class ScorePostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "score_type", columnDefinition = "TINYINT")
    private Byte scoreType;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;

    public Byte getScoreType() {
        return scoreType;
    }

    public void setScoreType(Byte scoreType) {
        this.scoreType = scoreType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }
}
