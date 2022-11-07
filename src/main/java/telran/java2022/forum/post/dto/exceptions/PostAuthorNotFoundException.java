package telran.java2022.forum.post.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PostAuthorNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2898404505161550864L;

	public PostAuthorNotFoundException(String author) {
		super("Author  " + author + " not found");
	}
}
