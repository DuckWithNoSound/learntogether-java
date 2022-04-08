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
}
