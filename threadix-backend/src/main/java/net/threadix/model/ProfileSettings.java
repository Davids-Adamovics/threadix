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
@Table(name = "PROFILE_SETTINGS")
@Entity
public class ProfileSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "privacy_level", nullable = false)
    private enumPrivacy_level privacyLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_settings", nullable = false)
    private enumNotification_settings notificationSettings;

    public ProfileSettings(int id, enumPrivacy_level privacyLevel, enumNotification_settings notificationSettings) {
        this.id = id;
        this.privacyLevel = privacyLevel;
        this.notificationSettings = notificationSettings;
    }
}
