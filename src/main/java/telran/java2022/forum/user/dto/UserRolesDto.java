package telran.java2022.forum.user.dto;

import java.util.EnumSet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.java2022.forum.user.model.Role;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserRolesDto {
	String login;
	EnumSet<Role> roles;
	
//	public UserRolesDto(String login, EnumSet<Role> roles) {
//		this.login = login;
//		this.roles = roles;
//	}
}
