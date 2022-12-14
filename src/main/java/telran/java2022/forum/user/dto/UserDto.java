package telran.java2022.forum.user.dto;

import java.util.EnumSet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.java2022.forum.user.model.Role;

@NoArgsConstructor
@Getter
@Builder
@Setter
@AllArgsConstructor
public class UserDto {
	String login;
	String firstName;
	String lastName;
	EnumSet<Role> roles;

//	public UserDto(String login, String firstName, String lastName, EnumSet<Role> roles) {
//		this.login = login;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.roles = roles;
//	}

}
