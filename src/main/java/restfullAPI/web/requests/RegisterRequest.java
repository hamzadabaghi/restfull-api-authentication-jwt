package restfullAPI.web.requests;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements Serializable {

	@Transient
	private static final long serialVersionUID = -3729702016721913486L;

	@NotBlank(message = "firstName ne doit pas etre null")
	@Size(min = 3)
	private String firstName;

	@NotBlank(message = "lastName doit pas etre null")
	@Size(min = 3, message = "lastName doit pas etre null")
	private String lastName;

	@NotBlank(message = "email ne doit pas etre null")
	@Email(message = "email doit respecter le bon format")

	private String email;

	@NotBlank(message = "password ne doit pas etre null")
	@Size(min = 8, message = "password doit avoir au minimum 8 caracteres")
	@Size(max = 12, message = "password doit avoir au maximum 12 caracteres")
	// @Pattern(regexp="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$\r\n" , message
	// ="le mot de passe ne respecte pas le format")
	private String password;

	@NotBlank(message = "repassword ne doit pas etre null")
	@Size(min = 8, message = "repassword doit avoir au minimum 8 caracteres")
	@Size(max = 12, message = "repassword doit avoir au maximum 12 caracteres")
	// @Pattern(regexp="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$\r\n" , message
	// ="le mot de passe ne respecte pas le format")
	private String repassword;

	private List<AddressRequest> addresses;

	private ContactRequest contact;
}

/*
 * test :
 * 
 * "firstName" : "hamza" , "lastName" : "dabaghi" , "email" :
 * "hamza.dabaghi@gmail.com" , "password" : "password" , "repassword" :
 * "password"
 * 
 * 
 */
