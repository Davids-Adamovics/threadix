package net.threadix.service.impl;

import java.lang.foreign.Linker.Option;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.threadix.DTO.LoginDTO;
import net.threadix.DTO.UserDTO;
import net.threadix.model.LoginMessage;
import net.threadix.model.User;
import net.threadix.repo.IUserRepo;
import net.threadix.service.IUserService;

@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // CRUD
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

    // LOGIN / REGISTER
    @Override
    public String addUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getUsername(),
                userDTO.getDisplayName(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword()) // Keep password hashed
        );

        userRepo.save(user);
        return user.getUsername();
    }

    UserDTO userDTO;

    @Override
    public LoginMessage loginUser(LoginDTO loginDTO) {
        User user1 = userRepo.findByEmail(loginDTO.getEmail());

        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPassRight = passwordEncoder.matches(password, encodedPassword);

            if (isPassRight) {
                return new LoginMessage("Login Success", true);
            } else {
                return new LoginMessage("Password Not Match", false);
            }
        } else {
            return new LoginMessage("Email not exists", false);
        }
    }

}
