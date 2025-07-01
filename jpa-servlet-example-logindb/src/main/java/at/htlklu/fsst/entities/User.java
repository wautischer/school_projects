package at.htlklu.fsst.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 45)
    private String username;

    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @Column(name = "firstname", nullable = false, length = 20)
    private String firstname;

    @Column(name = "surname", nullable = false, length = 25)
    private String surname;

    @Column(name = "email", nullable = false, length = 60)
    private String email;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Comment> commentsByID;

    public User(String username, String password, String firstname, String surname, String email) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        commentsByID = new HashSet<>();
    }

    public User() {
        commentsByID = new HashSet<>();
    }

    public void addComment(Comment comment) {
        this.commentsByID.add(comment);
        comment.setUser(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Comment> getCommentsByID() {
        return commentsByID;
    }

    public void setCommentsByID(Set<Comment> commentsByID) {
        this.commentsByID = commentsByID;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}