package restfullAPI.service;

import java.util.List;

import restfullAPI.entities.AppRole;
import restfullAPI.shared.dto.UserDto;

public interface AccountService {

	public UserDto saveUser(UserDto userDto);

	public AppRole saveRole(AppRole role);

	public void addRoleToUser(String email, String roleName);

	public UserDto findUserByEmail(String email);

	public List<UserDto> getUsers(int page, int limit);

	public UserDto getUserByUserId(String userId);

	public UserDto updateUser(String id, UserDto userDto);

	public void deleteUser(String id);

}
