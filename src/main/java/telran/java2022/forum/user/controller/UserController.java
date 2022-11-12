package telran.java2022.forum.user.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java2022.forum.user.dto.RegisterUserDto;
import telran.java2022.forum.user.dto.UpdateUserDto;
import telran.java2022.forum.user.dto.UserDto;
import telran.java2022.forum.user.dto.UserRolesDto;
import telran.java2022.forum.user.model.Role;
import telran.java2022.forum.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class UserController {
	final UserService userService;

	@PostMapping("/register")
	public UserDto register(@RequestBody RegisterUserDto registerUserDto) {
		return userService.register(registerUserDto);
	}

	@PostMapping("/login")
	public UserDto login(Principal principal) {
		return userService.login(principal.getName());
	}

	@DeleteMapping("/user/{user}")
	public UserDto deleteUser(@PathVariable String user) {
		return userService.deleteUser(user);
	}

	@PutMapping("/user/{user}")
	public UserDto updateUser(@PathVariable String user, @RequestBody UpdateUserDto updateUserDto) {
		return userService.updateUser(updateUserDto, user);
	}

	@PutMapping("/user/{user}/role/{role}")
	public UserRolesDto addRole(@PathVariable String user, @PathVariable String role) {
		Role rl;
		try {
			rl = Role.valueOf(role.toUpperCase());
		} catch (Exception e) {
			rl = Role.GUEST;
		}
		return userService.addRole(user, rl);
	}

	@DeleteMapping("/user/{user}/role/{role}")
	public UserRolesDto deleteRole(@PathVariable String user, @PathVariable String role) {
		Role rl;
		try {
			rl = Role.valueOf(role.toUpperCase());
			return userService.deleteRole(user, rl);
		} catch (Exception e) {
			return userService.addRole(user, Role.GUEST);
		}
	}

	@PutMapping("/password")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void changePassword(Principal principal, @RequestHeader("X-Password") String newPassword) {
		userService.changePassword(principal.getName(), newPassword);
	}
}
