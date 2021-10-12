package restfullAPI.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "users")

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AppUser implements Serializable {

	@Transient
	private static final long serialVersionUID = 8760069786981456681L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String userId;

	@Column(nullable = false, length = 50)
	private String firstName;

	@Column(nullable = false, length = 50)
	private String lastName;

	@Column(nullable = false, length = 120, unique = true)
	private String email;

	@Column(nullable = false)
	private String encryptedPassword;

	@Column(unique = true)
	private String username;

	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<AppRole> roles = new ArrayList<AppRole>();

	@Column(nullable = true)
	private String emailVerificationToken;

	@Column(nullable = false)
	private Boolean emailVerificationStatus = false;

	// cascade all to create at the same type addresses , mappedBy to reference the
	// foreign key in the addresses entity
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<AddressEntity> addresses;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL )
	@JoinColumn()
	private ContactEntity contact;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="users")
	private Set<GroupEntity> groups = new HashSet<GroupEntity>();
	

}
