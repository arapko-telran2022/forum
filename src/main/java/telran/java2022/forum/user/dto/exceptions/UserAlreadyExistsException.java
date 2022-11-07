package telran.java2022.forum.user.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = -6281273886557031607L;

    public UserAlreadyExistsException(String login) {
	super("User with login = " + login + " already exists");
    }

}
