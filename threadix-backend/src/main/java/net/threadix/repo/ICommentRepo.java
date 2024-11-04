package net.threadix.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.threadix.model.Comment;

@Repository
public interface ICommentRepo extends CrudRepository<Comment, Integer> {
}
