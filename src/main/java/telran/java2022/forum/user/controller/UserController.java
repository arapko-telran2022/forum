package telran.java2022.forum.user.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java2022.forum.user.dto.RegisterDto;
import telran.java2022.forum.user.dto.UserDto;
import telran.java2022.forum.user.model.Role;
import telran.java2022.forum.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class UserController {
	final UserService userService;

	@PostMapping("/register")
	public UserDto register(@RequestBody RegisterDto registerDto) {
		return userService.register(registerDto);
	}

	@PostMapping("/login")
	public UserDto login(@RequestBody RegisterDto registerDto) {
		return userService.login(registerDto);
	}

	@DeleteMapping("/user/{user}")
	public UserDto deleteUser(@PathVariable String user) {
		return userService.deleteUser(user);
	}

	@PutMapping("/user/{user}")
	public UserDto updateUser(@PathVariable String user, @RequestBody RegisterDto registerDto) {
		return userService.updateUser(registerDto, user);
	}

	@PutMapping("/user/{user}/role/{role}")
	public UserDto addRole(@PathVariable String user, @PathVariable Role role) {
		return userService.addRole(user, role);
	}

	@DeleteMapping("/user/{user}/role/{role}")
	public UserDto deleteRole(@PathVariable String user, @PathVariable Role role) {
		return userService.deleteRole(user, role);
	}

	@PutMapping("/password")
	public void changePassword(@RequestBody RegisterDto registerDto) {
		userService.changePassword(registerDto);
	}
}
