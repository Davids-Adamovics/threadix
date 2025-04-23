package net.threadix.repo;

import org.springframework.data.repository.CrudRepository;
import net.threadix.model.Post;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepo extends JpaRepository<Post, Integer> {
    List<Post> findAllByOrderByTimestampDesc();
}
