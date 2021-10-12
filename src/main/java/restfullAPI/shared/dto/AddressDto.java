package restfullAPI.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
	
		private long id;
		private String addressId;
		private String city;
		private String country;
		private String street;
		private String postal_code;
		private String type;
		private UserDto user;

}
