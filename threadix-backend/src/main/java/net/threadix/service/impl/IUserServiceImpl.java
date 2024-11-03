package net.threadix.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.threadix.model.User;
import net.threadix.repo.IUserRepo;
import net.threadix.service.IUserService;

@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    private IUserRepo userRepo;

    @Override
    public void create(User user) {
        userRepo.save(user);
    }

    @Override
    public User retrieveById(int id) throws Exception {
        if (id < 0)
            throw new Exception("ID jābūt pozitīvam!");

        if (userRepo.existsById(id)) {
            return userRepo.findById(id).get();
        } else {
            throw new Exception("User ar šo id (" + id + ") neeksistē!");
        }
    }

    @Override
    public ArrayList<User> retrieveAll() throws Exception {
        if (userRepo.count() == 0)
            throw new Exception("Nav nevienas User!");

        return (ArrayList<User>) userRepo.findAll();
    }

    @Override
    public void updateById(int id, User user) throws Exception {
        User userForUpdate = retrieveById(id);

        userForUpdate.setUsername(user.getUsername());
        userForUpdate.setDisplayName(user.getDisplayName());
        userForUpdate.setEmail(user.getEmail());
        userForUpdate.setPassword(user.getPassword());
        userForUpdate.setProfilePicture(user.getProfilePicture());
        userForUpdate.setBio(user.getBio());
        userForUpdate.setAnonymous(user.isAnonymous());

        userRepo.save(userForUpdate);
    }

    @Override
    public void deleteById(int id) throws Exception {
        User userForDeletion = retrieveById(id);
        userRepo.delete(userForDeletion);
    }
}
