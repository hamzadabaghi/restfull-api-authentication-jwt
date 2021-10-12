package restfullAPI.web.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ContactRequest {

	private String mobile;
	private String skype;

}
