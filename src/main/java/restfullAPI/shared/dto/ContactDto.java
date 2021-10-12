package restfullAPI.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactDto {

	private long id;
	private String contactId;
	private String mobile;
	private String skype;
	private UserDto user;


}
