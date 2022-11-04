package baylor.edu.cartuning.data;
import org.springframework.data.repository.CrudRepository;

import baylor.edu.cartuning.User;


public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);
  
}
