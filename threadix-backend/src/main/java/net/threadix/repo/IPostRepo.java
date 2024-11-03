package net.threadix.repo;

import org.springframework.data.repository.CrudRepository;
import net.threadix.model.Post;

public interface IPostRepo extends CrudRepository<Post, Integer> {
       
}