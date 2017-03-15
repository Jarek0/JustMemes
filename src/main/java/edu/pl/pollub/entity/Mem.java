package edu.pl.pollub.entity;

import edu.pl.pollub.entity.enums.Status;

import javax.persistence.*;
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

    @NotNull
    private Timestamp createdDate;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @ManyToOne
    private User user;

    public Mem(){

    }

    public Mem(String title,String contentType,Timestamp createdDate,User user){
        this.title=title;
        this.fileType=contentType.substring(contentType.lastIndexOf("/") + 1);
        this.createdDate=createdDate;
        this.status=Status.ACCEPTED;
        this.user=user;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
