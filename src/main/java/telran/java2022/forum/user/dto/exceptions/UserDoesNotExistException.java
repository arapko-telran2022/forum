package telran.java2022.forum.user.dto.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserDoesNotExistException extends RuntimeException {

    private static final long serialVersionUID = 5753990183215473181L;

    public UserDoesNotExistException(String login) {
	super("User with login = " + login + " doesn't exist");
    }

}
