package springbootsample.model;

public class UserBuilder {
	
	private String userId;
	private String userName;
	private String email;
	
	
	public UserBuilder(String userId, String userName, String email) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
	}
	
	public UserBuilder() {
	}

	public UserBuilder id(String userId) {
		this.userId = userId;
		return this;
	}

	public UserBuilder name(String userName) {
		this.userName = userName;
		return this;
	}


	public UserBuilder email(String email) {
		this.email = email;
		return this;
	}
	
	public User build() {
		return new User(userId, userName, email);
	}

}
