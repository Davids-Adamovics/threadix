package net.threadix.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    @Setter(value = AccessLevel.NONE)
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
    private String bio; // bio

    @Column(name = "IS_ANNONYMOUS", nullable = false)
    private boolean isAnonymous;

    public User(int userId, String username, String displayName, String email, String password, String profilePicture, String bio, boolean isAnonymous) {
        this.userId = userId;
        this.username = username;
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.isAnonymous = isAnonymous;
    }
}
