package restfullAPI.web.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import restfullAPI.exceptions.UserException;
import restfullAPI.service.AccountService;
import restfullAPI.shared.dto.UserDto;
import restfullAPI.web.requests.RegisterRequest;
import restfullAPI.web.responses.ErrorMessages;
import restfullAPI.web.responses.UserResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users") // uri

public class AccountController {

	@Autowired
	private AccountService accountService;

	// userRequest pour faire la deserialisation d'un objet XML ou JSON a un objet
	// java

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> register(@RequestBody @Valid RegisterRequest userRegisterRequest)
			throws Exception {

		// verification du mot du passe localement

		if (!userRegisterRequest.getPassword().equals(userRegisterRequest.getRepassword()))
			throw new UserException(ErrorMessages.PASSWORD_DONT_MATCHES.getErrorMessage());

		// UserDto : l'objet de transfert de donnee entre les couches de notre
		// architecture

		/* before 
		 * 
		    UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userRegisterRequest, userDto);

		 */
		
		/*after */
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto  = modelMapper.map(userRegisterRequest, UserDto.class);

		// on envoit ce userDto à la service pour faire des traitement puis on recupere
		// un autre objet UserDto

		UserDto userInserted = accountService.saveUser(userDto);
		accountService.addRoleToUser(userInserted.getEmail(), "USER");

		// on fait le binding pour envoyer une reponse

		UserResponse userResponse  = modelMapper.map(userInserted, UserResponse.class);

		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);

	}

	// s'execute automatiquement lors d'une methode get à notre rest Controller , de
	// meme pour les autres

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<UserResponse> getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "1") int limit) {

		List<UserResponse> userResponse = new ArrayList<UserResponse>();

		List<UserDto> users = accountService.getUsers(page, limit);
		
		ModelMapper modelMapper = new ModelMapper();

		for (UserDto userDto : users) {
			UserResponse user  = modelMapper.map(userDto, UserResponse.class);
			userResponse.add(user);

		}

		return userResponse;
	}

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })

	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {

		UserDto userToFind = accountService.getUserByUserId(id);
		ModelMapper modelMapper = new ModelMapper();

		if (userToFind != null) {

			UserResponse userResponse  = modelMapper.map(userToFind, UserResponse.class);

			return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);

		}

		return null;

	}

	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody RegisterRequest userRequest) {

		// UserDto : l'objet de transfert de donnee entre les couches de notre
		// architecture
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto  = modelMapper.map(userRequest, UserDto.class);


		// on envoit ce userDto à la service pour faire des traitement puis on recupere
		// un autre objet UserDto

		UserDto userUpdated = accountService.updateUser(id, userDto);

		// on fait le binding pour envoyer une reponse

		UserResponse userResponse  = modelMapper.map(userUpdated, UserResponse.class);

		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);

	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<UserResponse> deleteUser(@PathVariable String id) {

		accountService.deleteUser(id);

		return new ResponseEntity<UserResponse>(HttpStatus.NO_CONTENT);
	}

}
