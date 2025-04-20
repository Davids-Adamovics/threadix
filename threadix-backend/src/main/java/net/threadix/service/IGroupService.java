package net.threadix.service;

import java.util.ArrayList;
import net.threadix.model.Group;

public interface IGroupService {

    void create(Group group);

    Group retrieveById(int id) throws Exception;

    ArrayList<Group> retrieveAll() throws Exception;

    void updateById(int id, Group group) throws Exception;

    void deleteById(int id) throws Exception;
}
