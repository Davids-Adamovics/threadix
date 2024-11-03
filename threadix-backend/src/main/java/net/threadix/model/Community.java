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
@Table(name = "COMMUNITY")
@Entity
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int communityId;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private String visibility;

    @Column(name = "member_count", nullable = false)
    private int memberCount;

    public Community(int communityId, String name, String description, String visibility, int memberCount) {
        this.communityId = communityId;
        this.name = name;
        this.description = description;
        this.visibility = visibility;
        this.memberCount = memberCount;
    }
}
