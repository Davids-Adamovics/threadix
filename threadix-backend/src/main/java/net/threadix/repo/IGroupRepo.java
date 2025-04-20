package net.threadix.repo;

import org.springframework.data.repository.CrudRepository;
import net.threadix.model.Group;

public interface IGroupRepo extends CrudRepository<Group, Integer> {
}
