package net.threadix.repo;

import org.springframework.data.repository.CrudRepository;
import net.threadix.model.User;

public interface IUserRepo extends CrudRepository<User, Integer> {
}
