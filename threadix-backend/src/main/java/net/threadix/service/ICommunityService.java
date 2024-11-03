package net.threadix.service;

import java.util.ArrayList;
import net.threadix.model.Community;

public interface ICommunityService {

    void create(Community community);

    Community retrieveById(int id) throws Exception;

    ArrayList<Community> retrieveAll() throws Exception;

    void updateById(int id, Community community) throws Exception;

    void deleteById(int id) throws Exception;
}
