package telran.java2022.forum.user.dao;

import org.springframework.data.repository.CrudRepository;

import telran.java2022.forum.user.model.User;

public interface UserRepository extends CrudRepository<User, String> {

}
