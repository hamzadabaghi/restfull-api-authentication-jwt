package restfullAPI.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppRole implements Serializable {

	@Transient
	private static final long serialVersionUID = 2440345212371985901L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String roleName;

}
