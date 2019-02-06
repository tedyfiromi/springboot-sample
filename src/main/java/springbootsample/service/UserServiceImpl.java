package springbootsample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springbootsample.model.User;
import springbootsample.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getListUsers() {
		return userRepository.getListUsers();
	}

	@Override
	public User insert(User user) {
		return userRepository.insert(user);
	}

	@Override
	public boolean deleteUserById(String userId) {
		return userRepository.deleteUserById(userId);		
	}

	@Override
	public User findById(String userId) {
		return userRepository.findById(userId);
	}

	@Override
	public User updateUser(User currentUser) {
		return userRepository.updateUser(currentUser);
	}

	@Override
	public User findByUserName(String userName) {
		return userRepository.findUserByUserName(userName);
	}

}
