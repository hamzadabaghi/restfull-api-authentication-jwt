package restfullAPI.web.responses;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

	/* attributes */

	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private List<AddressResponse> addresses;
	private ContactResponse contact;

}
