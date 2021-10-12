package restfullAPI.service.implementation;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restfullAPI.shared.Utils;
import restfullAPI.entities.AppRole;
import restfullAPI.entities.AppUser;
import restfullAPI.repositories.RoleRepository;
import restfullAPI.repositories.UserRepository;
import restfullAPI.service.AccountService;
import restfullAPI.shared.dto.AddressDto;
import restfullAPI.shared.dto.UserDto;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	Utils util;

	/* register */

	@Override
	public UserDto saveUser(UserDto userDto) {

		// si existe deja , cross row validation

		UserDto oldUser = findUserByEmail(userDto.getEmail());

		if (oldUser != null)
			throw new RuntimeException("le mot de passe ou l\'email est incorrect");

		// addresses
		
		for (int i = 0; i < userDto.getAddresses().size(); i++) {
			
			AddressDto addressDto = userDto.getAddresses().get(i);
			addressDto.setUser(userDto);
			addressDto.setAddressId(util.generateStringId(20));
			userDto.getAddresses().set(i, addressDto);
		}
		


		// contact 
		
		
		userDto.getContact().setContactId(util.generateStringId(20));
		userDto.getContact().setUser(userDto);
		
		// le user à ajouter

		ModelMapper modelMapper = new ModelMapper();	
		AppUser user  = modelMapper.map(userDto, AppUser.class);

		
		
		
		// userId

		user.setUserId(util.generateStringId(32));

		// le cryptage du mot de passe et insertion

		user.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		AppUser userInserted = userRepository.save(user);

		// l'envoi encore une fois
		
		UserDto userDtoInserted  = modelMapper.map(userInserted, UserDto.class);


		return userDtoInserted;

	}

	@Override
	public AppRole saveRole(AppRole role) {
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String email, String roleName) {
		AppRole role = roleRepository.findByRoleName(roleName);
		AppUser user = userRepository.findByEmail(email);
		user.getRoles().add(role);

	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {

		if (page > 0)
			page -= page;

		List<UserDto> usersDto = new ArrayList<UserDto>();

		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<AppUser> userPage = userRepository.findAll(pageableRequest);

		List<AppUser> users = userPage.getContent();

		ModelMapper modelMapper = new ModelMapper();	

		for (AppUser userEntity : users) {
			
			UserDto user = modelMapper.map(userEntity, UserDto.class);
			usersDto.add(user);

		}

		return usersDto;

	}

	/* find user by email */

	@Override
	public UserDto findUserByEmail(String email) {

		ModelMapper modelMapper = new ModelMapper();	
		AppUser userfound = userRepository.findByEmail(email);

		if (userfound != null) {
			
			UserDto userDtofound = modelMapper.map(userfound, UserDto.class);

			return userDtofound;
		} else {
			return null;
		}
	}

	/* Get User By User ID */

	@Override
	public UserDto getUserByUserId(String userId) {

		AppUser userfound = userRepository.findByUserId(userId);

		ModelMapper modelMapper = new ModelMapper();	

		if (userfound != null) {
			UserDto userDtofound = modelMapper.map(userfound, UserDto.class);

			return userDtofound;
		} else {
			return null;
		}
	}

	/* Update */

	@Override
	public UserDto updateUser(String id, UserDto userDto) {

		// si existe deja , cross row validation

		AppUser userToUpdate = userRepository.findByUserId(id);

		if (userToUpdate == null)
			throw new RuntimeException("le utilisateur ne existe pas");

		/* update */

		userToUpdate.setFirstName(userDto.getFirstName());
		userToUpdate.setLastName(userDto.getLastName());

		AppUser userUpdated = userRepository.save(userToUpdate);

		ModelMapper modelMapper = new ModelMapper();
		UserDto newUserDto = modelMapper.map(userUpdated, UserDto.class);

		return newUserDto;

	}

	/* delete user */

	@Override
	public void deleteUser(String id) {

		AppUser userToDelete = userRepository.findByUserId(id);

		if (userToDelete == null)
			throw new RuntimeException("le utilisateur ne existe pas");

		userRepository.delete(userToDelete);

	}

}
