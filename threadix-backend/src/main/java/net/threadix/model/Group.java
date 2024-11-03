package net.threadix.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "GROUPS")
@Entity
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "member_count", nullable = false)
    private int memberCount;

    public Group(int groupId, String name, String description, int memberCount) {
        this.groupId = groupId;
        this.name = name;
        this.description = description;
        this.memberCount = memberCount;
    }
}
