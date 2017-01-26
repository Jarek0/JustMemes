package edu.pl.pollub.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Dell on 2017-01-26.
 */
@Entity
@Table(name="images")
public class Image {
    @Id
    @GeneratedValue
    private long id;

    private String path;

    public long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
