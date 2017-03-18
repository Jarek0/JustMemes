package edu.pl.pollub.entity;

import edu.pl.pollub.entity.request.UserRegisterRequest;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by Dell on 2017-03-15.
 */
@Entity
@Table(name="users")
public class User {

    public User() {

    }

    public User(UserRegisterRequest userRegisterRequest){
        this.username=userRegisterRequest.getUsername();
        this.password=userRegisterRequest.getPassword();
        this.email=userRegisterRequest.getEmail();
    }

    public User(String username, String password, String passwordConfirm, String email) {
        this.username = username;
        this.password = password;
        this.email = email;

    }

    public User(String username, String password, String email, boolean enabled, boolean online, boolean banned, VerificationToken token, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.online = online;
        this.banned = banned;
        this.token = token;
        this.role = role;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    @Size(min = 6, max = 64)
    private String username;

    @NotNull
    @Size(max = 70)
    private String password;

    @NotNull
    @Column(unique = true)
    @Size(min = 5, max = 255)
    private String email;

    @NotNull
    private boolean enabled;

    @NotNull
    private boolean online;

    @NotNull
    private boolean banned;

    @OneToOne(mappedBy = "user")
    private VerificationToken token;

    @ManyToOne
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Mem> memes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean getBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public VerificationToken getToken() {
        return token;
    }

    public void setToken(VerificationToken token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Mem> getMemes() {
        return memes;
    }

    public void setMemes(Set<Mem> memes) {
        this.memes = memes;
    }
}
