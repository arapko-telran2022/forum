package telran.java2022.forum.user.dto;

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
}
