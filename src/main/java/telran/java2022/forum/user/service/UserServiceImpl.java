package telran.java2022.forum.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java2022.forum.user.dao.UserRepository;
import telran.java2022.forum.user.dto.RegisterDto;
import telran.java2022.forum.user.dto.UserDto;
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
	public UserDto register(RegisterDto registerDto) {
		if (userRepository.findById(registerDto.getLogin()).isPresent()) {
			throw new UserAlreadyExistsException(registerDto.getLogin());
		}
		User user = modelMapper.map(registerDto, User.class);
		user.addRole(Role.USER);
		return modelMapper.map(userRepository.save(user), UserDto.class);
	}

	@Override
	public UserDto login(RegisterDto registerDto) {
		User user = userRepository.findById(registerDto.getLogin())
				.orElseThrow(() -> new UserDoesNotExistException(registerDto.getLogin()));
		if (!(registerDto.getPassword().equals(user.getPassword()))) {
			throw new IncorrectPasswordException();
		}
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto deleteUser(String userString) {
		User user = userRepository.findById(userString).orElseThrow(() -> new UserDoesNotExistException(userString));
		UserDto userDto = modelMapper.map(user, UserDto.class);
		userRepository.deleteById(userString);
		return userDto;
	}

	@Override
	public UserDto updateUser(RegisterDto registerDto, String userString) {
		User user = userRepository.findById(userString).orElseThrow(() -> new UserDoesNotExistException(userString));
		user.setFirstName(registerDto.getFirstName());
		user.setLastName(registerDto.getLastName());
		return modelMapper.map(userRepository.save(user), UserDto.class);
	}

	@Override
	public UserDto addRole(String userString, Role role) {
		User user = userRepository.findById(userString).orElseThrow(() -> new UserDoesNotExistException(userString));
		user.addRole(role);
		return modelMapper.map(userRepository.save(user), UserDto.class);
	}

	@Override
	public UserDto deleteRole(String userString, Role role) {
		User user = userRepository.findById(userString).orElseThrow(() -> new UserDoesNotExistException(userString));
		if (user.deleteRole(role)) {
			return modelMapper.map(userRepository.save(user), UserDto.class);
		}
		throw new UserDoesNotHaveThisRoleAssignedException(role, userString);
	}

	@Override
	public void changePassword(RegisterDto registerDto) {
		User user = userRepository.findById(registerDto.getLogin())
				.orElseThrow(() -> new UserDoesNotExistException(registerDto.getLogin()));
		user.setPassword(registerDto.getPassword());
		userRepository.save(user);
	}

}
