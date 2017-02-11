package edu.pl.pollub.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.security.Timestamp;

/**
 * Created by Dell on 2017-01-26.
 */
@Entity
@Table(name="images")
public class Mem {
    @Id
    @GeneratedValue
    private long id;

    private String title;

    private String fileType;
/* this will be useful if sort memes by id is not good idea
    private Timestamp createDate;

    private Timestamp modifyDate;*/

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

  /*  public Timestamp getCreateDate() {
        return createDate;
    }

    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }*/

}
