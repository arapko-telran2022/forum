package telran.java2022.forum.user.model;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
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
	EnumSet<Role> roles = EnumSet.of(Role.Guest, Role.User);

	public User(String login, String password, String firstName, String lastName) {
		System.out.println();
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;	
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
