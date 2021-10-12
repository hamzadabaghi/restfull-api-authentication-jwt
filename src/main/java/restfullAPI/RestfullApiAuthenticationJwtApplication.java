
package restfullAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import restfullAPI.entities.AppRole;
import restfullAPI.repositories.TaskRepository;
import restfullAPI.service.AccountService;


@SpringBootApplication

public class RestfullApiAuthenticationJwtApplication implements CommandLineRunner {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(RestfullApiAuthenticationJwtApplication.class, args);
	}

	@Bean

	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {

		accountService.saveRole(new AppRole(null, "ADMIN"));
		accountService.saveRole(new AppRole(null, "USER"));

		/*
		 * users
		 * 
		 * accountService.saveUser(new UserDto(null,,"admin","admin",1234,null));
		 * accountService.saveUser(new UserDto(null,,"user","user",1234",null));
		 * 
		 * roles
		 * 
		 * accountService.saveRole(new AppRole(null,"ADMIN"));
		 * accountService.saveRole(new AppRole(null,"USER"));
		 * 
		 * 
		 * Affectation accountService.addRoleToUser("admin", "ADMIN");
		 * accountService.addRoleToUser("user", "USER");
		 * 
		 * 
		 */

	}
}
