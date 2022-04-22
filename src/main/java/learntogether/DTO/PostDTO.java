package learntogether.DTO;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
  Created by Luvbert
*/
public class PostDTO extends AbstractDTO{
    private String title;
    private String content;
    private String image;
    private Integer viewNumber;
    private Integer score;
    private String[] listTagSlug;
    private String authorName;
    private Long[] ids;
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

    public List<String> getListTagSlug() {
        List<String> list = Arrays.asList(listTagSlug);
        return list;
    }

    public void setListTagSlug(List<String> listTagSlug) {
        this.listTagSlug = listTagSlug.toArray(new String[0]);
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }
}
