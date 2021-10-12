package restfullAPI.service.implementation;

import java.util.ArrayList;
import java.util.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import restfullAPI.entities.AppUser;
import restfullAPI.service.AccountService;
import restfullAPI.shared.dto.UserDto;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AccountService accountService;

	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserDto userDto = accountService.findUserByEmail(email);

		
		ModelMapper modelMapper = new ModelMapper();
		
		AppUser user  = modelMapper.map(userDto, AppUser.class);

		if (user == null)
			throw new UsernameNotFoundException(email);

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		user.getRoles().forEach(r -> {
			authorities.add(new SimpleGrantedAuthority(r.getRoleName()));

		});

		return new User(user.getEmail(), user.getEncryptedPassword(), authorities);
	}

}
