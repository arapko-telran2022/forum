package telran.java2022.forum.user.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.java2022.forum.user.model.Role;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    String login;
    String firstName;
    String lastName;
    Set<Role> roles;

    public UserDto(String login, String firstName, String lastName) {
	this.login = login;
	this.firstName = firstName;
	this.lastName = lastName;
	roles = new HashSet<>();
	roles.add(Role.GUEST);
    }

}
