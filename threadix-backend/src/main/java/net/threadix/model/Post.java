package net.threadix.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "image")
    private String imagePath;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private enumVisibility visibility;

    @Column(name = "likes_count", nullable = false)
    private int likesCount;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Post(String title, String content, LocalDateTime timestamp, enumVisibility visibility,
            int likesCount, List<Comment> comments) {
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
        this.visibility = visibility;
        this.likesCount = likesCount;
        this.comments = comments != null ? comments : new ArrayList<>();
    }

}
