package restfullAPI.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import restfullAPI.security.filters.JWTAuthenticationFilter;
import restfullAPI.security.filters.JWTAuthorizationFilter;
import restfullAPI.security.jwt.SecurityConstants;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// http.formLogin();
		// http.authorizeRequests().antMatchers(HttpMethod.POST,"/tasks/**").hasAuthority("ADMIN");
		// http.authorizeRequests().antMatchers("/login/**",
		// "/register/**").permitAll();

		http.cors();
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()
		.antMatchers("/v2/api-docs","/swagger-resources/**","/swagger-ui.html/**","/webjars/**").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

	}

}
