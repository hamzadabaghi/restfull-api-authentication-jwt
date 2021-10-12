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

@Entity(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Task implements Serializable {

	@Transient
	private static final long serialVersionUID = -5360798576809341793L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String taskName;

}
