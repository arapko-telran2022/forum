package telran.java2022.forum.security.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import telran.java2022.forum.user.dao.UserRepository;
import telran.java2022.forum.user.dto.exceptions.UserDoesNotExistException;
import telran.java2022.forum.user.model.Role;
import telran.java2022.forum.user.model.User;

@Component
@RequiredArgsConstructor
@Order(10)
public class AuthenticationFilter implements Filter {

	final UserRepository userRepository;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		if (!checkEndPoint(request.getMethod(), request.getServletPath())) {
			String token = request.getHeader("Authorization");
			String[] credentials;
			try {
				credentials = getCredentialsFromToken(token);
			} catch (Exception e) {
				response.sendError(401, "Invalid token");
				return;
			}

			User ua = userRepository.findById(credentials[0])
					.orElseThrow(() -> new UserDoesNotExistException(credentials[0]));
			Boolean checkAuth = BCrypt.checkpw(credentials[1], ua.getPassword());
			Boolean checkRole = ua.getRoles().contains(Role.USER);
			if (token == null || !checkAuth || !checkRole) {
				response.sendError(401, "Login or password is invalid");
				return;
			}
			request = new WrappedRequest(request, ua.getLogin());
		}
		chain.doFilter(request, response);
	}

	private String[] getCredentialsFromToken(String token) {
		String[] basicAuth = token.split(" ");
		String decode = new String(Base64.getDecoder().decode(basicAuth[1]));
		String[] credentials = decode.split(":");
		return credentials;
	}

	private Boolean checkEndPoint(String method, String servletPath) {
		return ("POST".equalsIgnoreCase(method) && servletPath.matches("/account/register?")
				|| (("POST".equalsIgnoreCase(method) || "GET".equalsIgnoreCase(method))
						&& servletPath.matches("/forum/posts.*")));
	}

	private class WrappedRequest extends HttpServletRequestWrapper {
		String login;

		public WrappedRequest(HttpServletRequest request, String login) {
			super(request);
			this.login = login;
		}

		@Override
		public Principal getUserPrincipal() {
			return () -> login;
		}
	}

}
