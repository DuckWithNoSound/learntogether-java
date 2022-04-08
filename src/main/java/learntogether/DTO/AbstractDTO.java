package learntogether.DTO;

import java.sql.Timestamp;

/*
  Created by Luvbert
*/
public class AbstractDTO {
    private Long id;
    private Timestamp createdDate;
    private Timestamp modifiedDate;

    public boolean isExist()
    {
        if(id != null) return true;
        return false;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }
    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

}
