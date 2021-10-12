package restfullAPI.shared.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto implements Serializable {

	/* il sert pour l'indexation de l'objet */

	private static final long serialVersionUID = -3067358646366562588L;

	/*
	 * attributes : this is the object transferred from controllers to service and
	 * from service to data layer as well
	 */

	private long id;
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String encryptedPassword;
	private String emailVerificationToken;
	private Boolean emailVerificationStatus = false;
	private List<AddressDto> addresses;
	private ContactDto contact;

}
