package net.threadix.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.threadix.model.ProfileSettings;
import net.threadix.repo.IProfileSettingsRepo;
import net.threadix.service.IProfileSettingsService;

@Service
public class IProfileSettingsServiceImpl implements IProfileSettingsService {

    @Autowired
    private IProfileSettingsRepo profileSettingsRepo;

    @Override
    public void create(ProfileSettings profileSettings) {
        profileSettingsRepo.save(profileSettings);
    }

    @Override
    public ProfileSettings retrieveById(int id) throws Exception {
        if (id < 0)
            throw new Exception("ID jābūt pozitīvam!");

        if (profileSettingsRepo.existsById(id)) {
            return profileSettingsRepo.findById(id).get();
        } else {
            throw new Exception("ProfileSettings ar šo id (" + id + ") neeksistē!");
        }
    }

    @Override
    public ArrayList<ProfileSettings> retrieveAll() throws Exception {
        if (profileSettingsRepo.count() == 0)
            throw new Exception("Nav neviena ProfileSettings!");

        return (ArrayList<ProfileSettings>) profileSettingsRepo.findAll();
    }

    @Override
    public void updateById(int id, ProfileSettings profileSettings) throws Exception {
        ProfileSettings profileSettingsForUpdate = retrieveById(id);

        profileSettingsForUpdate.setPrivacyLevel(profileSettings.getPrivacyLevel());
        profileSettingsForUpdate.setNotificationSettings(profileSettings.getNotificationSettings());

        profileSettingsRepo.save(profileSettingsForUpdate);
    }

    @Override
    public void deleteById(int id) throws Exception {
        ProfileSettings profileSettingsForDeletion = retrieveById(id);
        profileSettingsRepo.delete(profileSettingsForDeletion);
    }
}
