package edu.pl.pollub.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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

    private String title;

    private String fileType;

    private Timestamp createdDate;


    public Mem(){

    }

    public Mem(String title,String fileType,Timestamp createdDate){
        this.title=title;
        this.fileType=fileType;
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
