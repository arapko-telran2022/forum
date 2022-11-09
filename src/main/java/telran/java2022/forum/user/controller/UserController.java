package telran.java2022.forum.user.controller;

import java.util.Base64;

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
import telran.java2022.forum.user.dto.LoginUserDto;
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

//	@PostMapping("/login")
//	public UserDto login(@RequestBody LoginUserDto loginUserDto) {
//		return userService.login(loginUserDto);
//	}
	
	@PostMapping("/login")
	public UserDto login(@RequestHeader("Authorization") String token) {
		String[] basicAuth = token.split(" ");
		String decode = new String(Base64.getDecoder().decode(basicAuth[1]));
		String[] credentials = decode.split(":");
		return userService.login(credentials[0]);
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
	public UserRolesDto addRole(@PathVariable String user, @PathVariable Role role) {
		return userService.addRole(user, role);
	}

	@DeleteMapping("/user/{user}/role/{role}")
	public UserRolesDto deleteRole(@PathVariable String user, @PathVariable Role role) {
		return userService.deleteRole(user, role);
	}

	@PutMapping("/password")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void changePassword(@RequestBody LoginUserDto changePasswordDto) {
		userService.changePassword(changePasswordDto);
	}
}
