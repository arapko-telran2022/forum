package telran.java2022.forum.user.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "login")
@Document(collection = "user")
@ToString
public class User {
	@Id
	String login;
	@Setter
	String firstName;
	@Setter
	String lastName;
	@Setter
	String password;
	Set<Role> roles = new HashSet<>();

	public User(String login, String firstName, String lastName) {
		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
		roles.add(Role.GUEST);
		roles.add(Role.USER);
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}

	public Boolean deleteRole(Role role) {
		if (this.roles.contains(role)) {
			this.roles.remove(role);
			return true;
		}
		return false;
	}

}
