package restfullAPI.security.filters;

import java.io.IOException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import restfullAPI.security.jwt.SecurityConstants;
import restfullAPI.web.requests.UserLoginRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		UserLoginRequest userLoginRequest = null;
		try {
			userLoginRequest = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequest.class);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(), userLoginRequest.getPassword()));

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		User springUser = (User) authResult.getPrincipal();
		String jwt = Jwts.builder().setSubject(springUser.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
				.claim("roles", springUser.getAuthorities()).compact();
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + jwt);

	}

}
