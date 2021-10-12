package restfullAPI.web.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// pour emporter la requete de login

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {

	private String email;
	private String password;

}

/*
 * test :
 * 
 * "email" : "hamza.dabaghi@gmail.com" , "password" : "password"
 * 
 * 
 * 
 */
