package net.threadix.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "COMMENT")
@Entity
public class Comment {

    @Id
    @Column(name = "ID_COMMENT")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int commentId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "likes_count", nullable = false)
    private int likesCount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment(String content, LocalDateTime timestamp, int likesCount, User user, Post post) {
        this.content = content;
        this.timestamp = timestamp;
        this.likesCount = likesCount;
        this.user = user;
        this.post = post;
    }
}
