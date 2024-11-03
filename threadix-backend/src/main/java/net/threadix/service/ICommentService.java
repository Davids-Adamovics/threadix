package net.threadix.service;

import java.util.ArrayList;
import net.threadix.model.Comment;

public interface ICommentService {

    void create(Comment comment);

    Comment retrieveById(int id) throws Exception;

    ArrayList<Comment> retrieveAll() throws Exception;

    void updateById(int id, Comment comment) throws Exception;

    void deleteById(int id) throws Exception;
}
