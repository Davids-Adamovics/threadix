package net.threadix.service;

import java.util.ArrayList;
import net.threadix.model.ProfileSettings;

public interface IProfileSettingsService {

    void create(ProfileSettings profileSettings);

    ProfileSettings retrieveById(int id) throws Exception;

    ArrayList<ProfileSettings> retrieveAll() throws Exception;

    void updateById(int id, ProfileSettings profileSettings) throws Exception;

    void deleteById(int id) throws Exception;
}
