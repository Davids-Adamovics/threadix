package net.threadix.model;

import jakarta.persistence.*;
import lombok.*;
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

    @ManyToMany
    @JoinTable(
        name = "USER_GROUPS_JOIN",
        joinColumns = @JoinColumn(name = "USER_ID"),
        inverseJoinColumns = @JoinColumn(name = "GROUP_ID")
    )
    private List<Group> groups;

    @ManyToMany
    @JoinTable(
        name = "USER_COMMUNITIES_JOIN",
        joinColumns = @JoinColumn(name = "USER_ID"),
        inverseJoinColumns = @JoinColumn(name = "COMMUNITY_ID")
    )
    private List<Community> communities;

    @OneToMany(mappedBy = "user")  // Referencing the "user" field in Comment entity
    private List<Comment> comments;

    public User(String username, String displayName, String email, String password, String profilePicture, String bio, boolean isAnonymous) {
        this.username = username;
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.isAnonymous = isAnonymous;
    }
}
