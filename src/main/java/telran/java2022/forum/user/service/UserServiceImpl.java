package telran.java2022.forum.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java2022.forum.user.dao.UserRepository;
import telran.java2022.forum.user.dto.LoginUserDto;
import telran.java2022.forum.user.dto.RegisterUserDto;
import telran.java2022.forum.user.dto.UpdateUserDto;
import telran.java2022.forum.user.dto.UserDto;
import telran.java2022.forum.user.dto.UserRolesDto;
import telran.java2022.forum.user.dto.exceptions.IncorrectPasswordException;
import telran.java2022.forum.user.dto.exceptions.UserAlreadyExistsException;
import telran.java2022.forum.user.dto.exceptions.UserDoesNotExistException;
import telran.java2022.forum.user.dto.exceptions.UserDoesNotHaveThisRoleAssignedException;
import telran.java2022.forum.user.model.Role;
import telran.java2022.forum.user.model.User;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	final ModelMapper modelMapper;
	final UserRepository userRepository;

	@Override
	public UserDto register(RegisterUserDto registerUserDto) {
		if (userRepository.findById(registerUserDto.getLogin()).isPresent()) {
			throw new UserAlreadyExistsException(registerUserDto.getLogin());
		}
		User user = modelMapper.map(registerUserDto, User.class);
		user = userRepository.save(user);
		return new UserDto(user.getLogin(), user.getFirstName(), user.getLastName(), user.getRoles());
	}

	@Override
	public UserDto login(LoginUserDto loginUserDto) {
		User user = userRepository.findById(loginUserDto.getLogin())
				.orElseThrow(() -> new UserDoesNotExistException(loginUserDto.getLogin()));
		if (!(loginUserDto.getPassword().equals(user.getPassword()))) {
			throw new IncorrectPasswordException();
		}
		return new UserDto(user.getLogin(), user.getFirstName(), user.getLastName(), user.getRoles());
	}

	@Override
	public UserDto deleteUser(String userString) {
		User user = userRepository.findById(userString).orElseThrow(() -> new UserDoesNotExistException(userString));
		UserDto userDto = new UserDto(user.getLogin(), user.getFirstName(), user.getLastName(), user.getRoles());
		userRepository.deleteById(userString);
		return userDto;
	}

	@Override
	public UserDto updateUser(UpdateUserDto updateUserDto, String userString) {
		User user = userRepository.findById(userString).orElseThrow(() -> new UserDoesNotExistException(userString));
		user.setFirstName(updateUserDto.getFirstName());
		user.setLastName(updateUserDto.getLastName());
		user = userRepository.save(user);
		return new UserDto(user.getLogin(), user.getFirstName(), user.getLastName(), user.getRoles());
	}

	@Override
	public UserRolesDto addRole(String userString, Role role) {
		User user = userRepository.findById(userString).orElseThrow(() -> new UserDoesNotExistException(userString));
		user.addRole(role);
		user = userRepository.save(user);
		return new UserRolesDto(user.getLogin(),user.getRoles());
	}

	@Override
	public UserRolesDto deleteRole(String userString, Role role) {
		User user = userRepository.findById(userString).orElseThrow(() -> new UserDoesNotExistException(userString));
		if (user.deleteRole(role)) {
			user = userRepository.save(user);
			return new UserRolesDto(user.getLogin(),user.getRoles());
		}
		throw new UserDoesNotHaveThisRoleAssignedException(role, userString);
	}

	@Override
	public void changePassword(LoginUserDto changePasswordDto) {
		User user = userRepository.findById(changePasswordDto.getLogin())
				.orElseThrow(() -> new UserDoesNotExistException(changePasswordDto.getLogin()));
		user.setPassword(changePasswordDto.getPassword());
		userRepository.save(user);
	}

}
