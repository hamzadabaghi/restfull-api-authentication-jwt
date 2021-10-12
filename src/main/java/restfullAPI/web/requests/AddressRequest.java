package restfullAPI.web.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

	private String city;
	private String country;
	private String street;
	private String postal_code;
	private String type;

}

/*
 * 
 * 
 * 
 * 
 * 
 * 
 * "city" : "Kenitra", "country" : "Morocco", "street" : "hay soukayna fes",
 * "postal_code" : "30000", "type" : "billing"
 * 
 * 
 * 
 * 
 * 
 * 
 */