package net.threadix.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "USER")
@Entity
public class User {

    @Id
    @Column(name = "ID_USER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false, unique = true, name = "USERNAME")
    private String username;

    @Column(name = "DISPLAY_NAME", nullable = false)
    private String displayName;

    @Column(nullable = false, unique = true, name = "EMAIL")
    private String email;

    @Column(nullable = false, name = "PASSWORD")
    private String password;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(length = 500, name = "BIO")
    private String bio;

    @Column(name = "IS_ANNONYMOUS", nullable = false)
    private boolean isAnonymous;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PROFILE_SETTINGS_ID", referencedColumnName = "ID_PROFILE_SETTINGS")
    private ProfileSettings profileSettings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public User(String username, String displayName, String email, String password, String profilePicture, String bio,
            boolean isAnonymous, List<Comment> comments) {
        this.username = username;
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.isAnonymous = isAnonymous;
        this.comments = comments != null ? comments : new ArrayList<>();
    }

}
