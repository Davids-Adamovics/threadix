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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    private int commentId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "likes_count", nullable = false)
    private int likesCount;

    public Comment(int commentId, String content, LocalDateTime timestamp, int likesCount) {
        this.commentId = commentId;
        this.content = content;
        this.timestamp = timestamp;
        this.likesCount = likesCount;
    }
}
