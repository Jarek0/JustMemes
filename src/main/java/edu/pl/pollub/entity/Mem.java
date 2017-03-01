package edu.pl.pollub.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Created by Dell on 2017-01-26.
 */
@Entity
@Table(name="memes")
public class Mem {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(min = 2, max = 35)
    private String title;

    @NotNull @Size(min=3,max=4) @Pattern(regexp = "^(gif|jpeg|png|mp4)",message="invalid file type")
    private String fileType;

    private Timestamp createdDate;


    public Mem(){

    }

    public Mem(String title,String contentType,Timestamp createdDate){
        this.title=title;
        this.fileType=contentType.substring(contentType.lastIndexOf("/") + 1);
        this.createdDate=createdDate;
    }

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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }


}
