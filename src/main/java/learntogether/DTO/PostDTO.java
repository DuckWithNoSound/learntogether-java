package learntogether.DTO;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;

/*
  Created by Luvbert
*/
public class PostDTO extends AbstractDTO{
    @NotNull
    @NotEmpty
    private String title;
    @NotNull
    @NotEmpty
    private String content;
    private String image;
    private Integer viewNumber;
    private Integer score;
    @NotNull
    @NotEmpty
    private String[] listTagSlug;
    @NotNull
    @NotEmpty
    private String authorName;

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String[] getListTagSlug() {
        return listTagSlug;
    }

    public void setListTagSlug(String[] listTagSlug) {
        this.listTagSlug = listTagSlug;
    }
}
