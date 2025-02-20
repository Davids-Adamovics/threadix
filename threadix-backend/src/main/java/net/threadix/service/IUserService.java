package net.threadix.service;

import java.util.ArrayList;

import net.threadix.DTO.LoginDTO;
import net.threadix.DTO.UserDTO;
import net.threadix.model.LoginMessage;
import net.threadix.model.User;

public interface IUserService {

    // CRUD 
    void create(User user);

    User retrieveById(int id) throws Exception;

    ArrayList<User> retrieveAll() throws Exception;

    void updateById(int id, User user) throws Exception;

    void deleteById(int id) throws Exception;

    // LOGIN / REGISTER
    String addUser(UserDTO userDTO);

    LoginMessage loginUser(LoginDTO loginDTO);

}
