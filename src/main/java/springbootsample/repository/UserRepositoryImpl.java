package springbootsample.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoExecutionTimeoutException;

import springbootsample.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository{
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<User> getListUsers() {	
		return mongoTemplate.findAll(User.class);
	}

	@Override
	public User insert(User user) {
		return mongoTemplate.save(user);
	}

	@Override
	public boolean deleteUserById(String userId) {
		Query query = new Query().addCriteria(Criteria.where("_id").is(userId));	
		return mongoTemplate.findAndRemove(query, User.class) != null;
	}

	@Override
	public User findById(String userId) {
		Query query = new Query().addCriteria(Criteria.where("_id").is(userId));			
		return mongoTemplate.findOne(query, User.class);
	}

	@Override
	public User updateUser(User currentUser) {
		return mongoTemplate.save(currentUser);
	}

	@Override
	public User findUserByUserName(String userName) {
		Query query = new Query().addCriteria(Criteria.where("userName").is(userName));			
		return mongoTemplate.findOne(query, User.class);
	}

}