package telran.java2022.forum.user.dto.exceptions;

import telran.java2022.forum.user.model.Role;

public class UserDoesNotHaveThisRoleAssignedException extends RuntimeException {
    private static final long serialVersionUID = -3941288944817787229L;

    public UserDoesNotHaveThisRoleAssignedException(Role role, String login) {
	super("User with login = " + login + " doesn't have role = " + role + " assigned");
    }

}
