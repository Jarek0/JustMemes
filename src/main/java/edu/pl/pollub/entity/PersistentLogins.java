package edu.pl.pollub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Dell on 2017-04-15.
 */
@Entity
@Table(name="persistent_logins")
public class PersistentLogins {
    PersistentLogins(){

    }

    public PersistentLogins(String username, String series, String token, Date lastUsed) {
        this.username = username;
        this.series = series;
        this.token = token;
        this.lastUsed = lastUsed;
    }

    @Size(max = 100)
    private String username;

    @Id
    @Column(nullable = false)
    @Size(max = 64)
    private String series;

    @NotNull
    @Size(max = 64)
    private String token;

    @NotNull
    private Date lastUsed;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }
}
