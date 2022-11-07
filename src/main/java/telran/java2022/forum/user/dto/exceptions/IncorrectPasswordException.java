package telran.java2022.forum.user.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class IncorrectPasswordException extends RuntimeException {
	private static final long serialVersionUID = -2264336430486344345L;

	public IncorrectPasswordException() {
		super("The password is incorrect");
	}
}
