package net.threadix.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "USER_GROUPS")
@Entity
public class Group {

    @Id
    @Column(name = "ID_GROUP")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int groupId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "member_count", nullable = false)
    private int memberCount;

    @ManyToMany(mappedBy = "groups")
    private List<User> users; // Optional for bidirectionality

    public Group(String name, String description, int memberCount) {
        this.name = name;
        this.description = description;
        this.memberCount = memberCount;
    }
}
