package telran.java2022;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import telran.java2022.forum.user.dao.UserRepository;
import telran.java2022.forum.user.model.Role;
import telran.java2022.forum.user.model.User;

@SpringBootApplication
public class ForumServiceApplication implements CommandLineRunner{

	@Autowired
	UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ForumServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(!repository.existsById("admin")) {
			String password = BCrypt.hashpw("admin", BCrypt.gensalt());
			User userAccount = new User("admin", password, "","");
			userAccount.addRole(Role.MODERATOR);
			userAccount.addRole(Role.ADMINISTARTOR);
			repository.save(userAccount);
		}
		
	}
}
