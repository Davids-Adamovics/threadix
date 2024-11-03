package net.threadix.repo;

import org.springframework.data.repository.CrudRepository;
import net.threadix.model.Comment;

public interface ICommentRepo extends CrudRepository<Comment, Integer> {
}
