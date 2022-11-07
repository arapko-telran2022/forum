package telran.java2022.forum.user.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import telran.java2022.forum.user.model.Role;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class UserDoesNotHaveThisRoleAssignedException extends RuntimeException {
    private static final long serialVersionUID = -3941288944817787229L;

    public UserDoesNotHaveThisRoleAssignedException(Role role, String login) {
	super("User with login = " + login + " doesn't have role = " + role + " assigned");
    }

}
