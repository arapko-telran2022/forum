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
import telran.java2022.forum.user.dao.UserRepository;
import telran.java2022.forum.user.model.Role;
import telran.java2022.forum.user.model.User;

@Component
@RequiredArgsConstructor
@Order(30)
public class AccountOwnerFilter implements Filter{

final UserRepository userRepository;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		String method = request.getMethod();
		String path = request.getServletPath();
		
		if(checkEndPoint(method, path)) {
			User ua = userRepository.findById(request.getUserPrincipal().getName()).get();
			Boolean checkRole = ua.getRoles().contains(Role.ADMINISTARTOR);		
			Boolean owner = path.split("/")[3].equals(ua.getLogin());		
			if(!(owner ||("DELETE".equalsIgnoreCase(method) && checkRole))) {
				response.sendError(403, "You aren't OWNER or ROLE is invalid");
				return;
			}
		}		
		chain.doFilter(request, response);		
	}
	
	private boolean checkEndPoint(String method, String servletPath) {
		return 	(
				(("PUT".equalsIgnoreCase(method)||  "DELETE".equalsIgnoreCase(method)) && servletPath.matches("/account/user/\\w+/?"))
				);
	}
	
}
