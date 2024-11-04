package net.threadix.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.threadix.model.Comment;
import net.threadix.repo.ICommentRepo;
import net.threadix.service.ICommentService;

@Service
public class ICommentServiceImpl implements ICommentService {

    @Autowired
    private ICommentRepo commentRepo;

    @Override
    public void create(Comment comment) {
        commentRepo.save(comment);
    }

    @Override
    public Comment retrieveById(int id) throws Exception {
        if (id < 0)
            throw new Exception("ID jābūt pozitīvam!");

        if (commentRepo.existsById(id)) {
            return commentRepo.findById(id).get();
        } else {
            throw new Exception("comment ar šo id (" + id + ") neeksistē!");
        }
    }

    @Override
    public ArrayList<Comment> retrieveAll() throws Exception {
        if (commentRepo.count() == 0)
            throw new Exception("Nav nevienas comment!");

        return (ArrayList<Comment>) commentRepo.findAll();
    }

    @Override
    public void updateById(int id, Comment comment) throws Exception {
        Comment commentForUpdate = retrieveById(id);

        commentForUpdate.setContent(comment.getContent());
        commentForUpdate.setTimestamp(comment.getTimestamp());
        commentForUpdate.setLikesCount(comment.getLikesCount());

        commentRepo.save(commentForUpdate);
    }

    @Override
    public void deleteById(int id) throws Exception {
        Comment pasakumsForDeletion = retrieveById(id);
        commentRepo.delete(pasakumsForDeletion);
    }

}
