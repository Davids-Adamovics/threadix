package net.threadix.service;

import java.util.ArrayList;
import net.threadix.model.Post;

public interface IPostService {

    void create(Post post);

    Post retrieveById(int id) throws Exception;

    ArrayList<Post> retrieveAll() throws Exception;

    void updateById(int id, Post post) throws Exception;

    void deleteById(int id) throws Exception;
}
