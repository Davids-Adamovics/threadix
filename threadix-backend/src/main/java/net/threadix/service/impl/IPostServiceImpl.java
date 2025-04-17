package net.threadix.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.threadix.model.Post;
import net.threadix.repo.IPostRepo;
import net.threadix.service.IPostService;

@Service
public class IPostServiceImpl implements IPostService {

    @Autowired
    private IPostRepo postRepo;

    @Override
    public void create(Post post) {
        postRepo.save(post);
    }

    @Override
    public Post retrieveById(int id) throws Exception {
        if (id < 0)
            throw new Exception("ID jābūt pozitīvam!");

        if (postRepo.existsById(id)) {
            return postRepo.findById(id).get();
        } else {
            throw new Exception("Post ar šo id (" + id + ") neeksistē!");
        }
    }

    @Override
    public ArrayList<Post> retrieveAll() throws Exception {
        if (postRepo.count() == 0) {
            return new ArrayList<>();
        }
        return (ArrayList<Post>) postRepo.findAll();
    }

    @Override
    public void updateById(int id, Post post) throws Exception {
        Post postForUpdate = retrieveById(id);

        postForUpdate.setTitle(post.getTitle());
        postForUpdate.setContent(post.getContent());
        postForUpdate.setTimestamp(post.getTimestamp());
        postForUpdate.setVisibility(post.getVisibility());
        postForUpdate.setLikesCount(post.getLikesCount());

        postRepo.save(postForUpdate);
    }

    @Override
    public void deleteById(int id) throws Exception {
        Post postForDeletion = retrieveById(id);
        postRepo.delete(postForDeletion);
    }
}
