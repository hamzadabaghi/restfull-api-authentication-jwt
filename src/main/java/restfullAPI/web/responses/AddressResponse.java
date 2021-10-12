package restfullAPI.web.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

	private String addressId;
	private String city;
	private String country;
	private String street;
	private String postal_code;
	private String type;
	
}
