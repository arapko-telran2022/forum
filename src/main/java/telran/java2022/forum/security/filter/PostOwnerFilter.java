package telran.java2022.forum.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import telran.java2022.forum.post.dao.PostRepository;
import telran.java2022.forum.post.dto.exceptions.PostNotFoundException;
import telran.java2022.forum.post.model.Post;
import telran.java2022.forum.user.dao.UserRepository;
import telran.java2022.forum.user.dto.exceptions.UserDoesNotExistException;
import telran.java2022.forum.user.model.Role;
import telran.java2022.forum.user.model.User;

@Component
@RequiredArgsConstructor
@Order(40)
public class PostOwnerFilter implements Filter {

	final UserRepository userRepository;
	final PostRepository postRepository;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String method = request.getMethod();
		String path = request.getServletPath();

		if (checkEndPoint(method, path)) {
			User ua = userRepository.findById(request.getUserPrincipal().getName()).get();
			if ("POST".equalsIgnoreCase(method)) {
				if (!path.split("/")[3].equals(ua.getLogin())) {
					response.sendError(403, "You aren't OWNER");
					return;
				}
			} else {
				Boolean checkRole = ua.getRoles().contains(Role.MODERATOR);
				Post post = postRepository.findById(path.split("/")[3]).orElse(null);
				if (post == null) {
					response.sendError(404, "Post not found");
					return;
				}
				Boolean owner = post.getAuthor().equals(ua.getLogin());
				if (!(owner || ("DELETE".equalsIgnoreCase(method) && checkRole))) {
					response.sendError(403, "You aren't OWNER or ROLE is invalid");
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	private boolean checkEndPoint(String method, String servletPath) {
		return ("PUT".equalsIgnoreCase(method) || "DELETE".equalsIgnoreCase(method) || "POST".equalsIgnoreCase(method))
				&& servletPath.matches("/forum/post/\\w+/?");
	}
}
