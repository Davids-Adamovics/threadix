package net.threadix.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "POST")
@Entity
public class Post {

    @Id
    @Column(name = "ID_POST")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int postId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private enumVisibility visibility;

    @Column(name = "likes_count", nullable = false)
    private int likesCount;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID_USER", nullable = false)
    private User user;

    public Post(String title, String content, LocalDateTime timestamp, enumVisibility visibility,
            int likesCount, User user) {
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
        this.visibility = visibility;
        this.likesCount = likesCount;
        this.user = user; // Set the user
    }

}
