package restfullAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import restfullAPI.entities.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

	public AppUser findByUsername(String username);

	public AppUser findByEmail(String email);

	public AppUser findByUserId(String userId);

}
