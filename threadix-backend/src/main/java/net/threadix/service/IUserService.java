package net.threadix.service;

import java.util.ArrayList;
import net.threadix.model.User;

public interface IUserService {

    void create(User user);

    User retrieveById(int id) throws Exception;

    ArrayList<User> retrieveAll() throws Exception;

    void updateById(int id, User user) throws Exception;

    void deleteById(int id) throws Exception;
}
