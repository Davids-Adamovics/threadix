package net.threadix.repo;

import org.springframework.data.repository.CrudRepository;
import net.threadix.model.Community;

public interface ICommunityRepo extends CrudRepository<Community, Integer> {
}
