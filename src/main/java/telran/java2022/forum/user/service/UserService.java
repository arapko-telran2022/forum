package telran.java2022.forum.user.service;

import telran.java2022.forum.user.dto.RegisterDto;
import telran.java2022.forum.user.dto.UserDto;
import telran.java2022.forum.user.model.Role;

public interface UserService {

    UserDto register(RegisterDto registerDto);

    UserDto login(RegisterDto registerDto);

    UserDto deleteUser(String user);

    UserDto updateUser(RegisterDto registerDto, String user);

    UserDto addRole(String user, Role role);

    UserDto deleteRole(String user, Role role);

    void changePassword(RegisterDto registerDto);

}
