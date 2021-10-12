package restfullAPI.web.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponse {

	private String contactId;
	private String mobile;
	private String skype;
	

	
}
