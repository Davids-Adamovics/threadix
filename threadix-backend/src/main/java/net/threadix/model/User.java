package net.threadix.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private int userId;

    @Column(nullable = false, unique = true, name = "USERNAME")
    private String username;

    @Column(name = "DISPLAY_NAME", nullable = false)
    private String displayName;

    @Column(nullable = false, unique = true, name = "EMAIL")
    private String email;

    @Column(nullable = false, name = "PASSWORD")
    private String password;

    @Column(name = "PROFILE_PICTURE")
    private String profilePicture;

    @Column(length = 500, name = "BIO")
    private String bio;

    @Column(name = "IS_ANONYMOUS", nullable = false)
    private boolean isAnonymous;

    @OneToOne
    @JoinColumn(name = "profile_settings_id")
    private ProfileSettings profileSettings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    // Constructor (for Registering Users)
    public User(String username, String displayName, String email, String password) {
        this.username = username;
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.profilePicture = null; // Default value
        this.bio = null; // Default value
        this.isAnonymous = false; // Default to false
        this.comments = new ArrayList<>();
    }

    // Existing Full Constructor (for when you need all fields)
    public User(String username, String displayName, String email, String password,
            String profilePicture, String bio, boolean isAnonymous, List<Comment> comments) {
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
