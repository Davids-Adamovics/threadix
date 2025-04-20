package net.threadix.model;

import java.util.Optional;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "PROFILE_SETTINGS")
@Entity
public class ProfileSettings {

    @Id
    @Column(name = "ID_PROFILE_SETTINGS")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "privacy_level", nullable = false)
    private enumPrivacy_level privacyLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_settings", nullable = false)
    private enumNotification_settings notificationSettings;

    @OneToOne(mappedBy = "profileSettings")
    private User user;

    public ProfileSettings(enumPrivacy_level privacyLevel, enumNotification_settings notificationSettings) {
        this.privacyLevel = privacyLevel;
        this.notificationSettings = notificationSettings;
    }
}
