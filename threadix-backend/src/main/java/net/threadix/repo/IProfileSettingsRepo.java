package net.threadix.repo;

import org.springframework.data.repository.CrudRepository;
import net.threadix.model.ProfileSettings;

public interface IProfileSettingsRepo extends CrudRepository<ProfileSettings, Integer> {
}
