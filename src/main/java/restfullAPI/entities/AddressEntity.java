package restfullAPI.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 8106560824187969451L;

	@Id
	@GeneratedValue
	private long id;

	@Column(length = 30, nullable = false)
	private String addressId;

	@Column(length = 20, nullable = false)
	private String city;

	@Column(length = 20, nullable = false)
	private String country;

	@Column(length = 50, nullable = false)
	private String street;

	@Column(length = 7, nullable = false)
	private String postal_code;

	@Column(length = 20, nullable = false)
	private String type;

	@ManyToOne
	@JoinColumn(name = "users_id")
	private AppUser user;

}
