package learntogether.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
  Created by Luvbert
*/
@Entity
@Table(name = "tag")
public class TagEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag_name")
    private String name;

    @Column(name = "tag_slug")
    private String slug;

    @ManyToMany(mappedBy = "tags")
    private List<PostEntity> posts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<PostEntity> posts) {
        this.posts = posts;
    }
}
