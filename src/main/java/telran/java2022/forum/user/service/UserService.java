package telran.java2022.forum.user.service;

import telran.java2022.forum.user.dto.LoginUserDto;
import telran.java2022.forum.user.dto.RegisterUserDto;
import telran.java2022.forum.user.dto.UpdateUserDto;
import telran.java2022.forum.user.dto.UserDto;
import telran.java2022.forum.user.dto.UserRolesDto;
import telran.java2022.forum.user.model.Role;

public interface UserService {

    UserDto register(RegisterUserDto registeUserDto);

    UserDto login(LoginUserDto loginUserDto);

    UserDto deleteUser(String user);

    UserDto updateUser(UpdateUserDto updateUserDto, String user); 

    UserRolesDto addRole(String user, Role role);

    UserRolesDto deleteRole(String user, Role role);

    void changePassword(LoginUserDto changePasswordDto);
 
}
