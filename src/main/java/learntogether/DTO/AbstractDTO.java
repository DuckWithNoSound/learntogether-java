package learntogether.DTO;


import java.util.Date;

/*
  Created by Luvbert
*/
public class AbstractDTO {
    private Long id;

    private Date createdDate;
    private Date modifiedDate;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
