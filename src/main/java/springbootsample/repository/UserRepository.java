package springbootsample.repository;

import java.util.List;

import springbootsample.model.User;

public interface UserRepository {
	
	public List<User> getListUsers();
	
	public User insert(User user);

	public boolean deleteUserById(String userId);

	public User findById(String userId);

	public User updateUser(User currentUser);

	public User findUserByUserName(String userName);

}
