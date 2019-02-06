package springbootsample.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springbootsample.model.User;
import springbootsample.response.Response;
import springbootsample.service.UserService;

@RestController
@RequestMapping(value="/sample")
public class UserController {
	
	private static final String ALL = "/all";
	private static final String INSERT = "/insert";
	private static final String HEALTH = "/health";
	private static final String DELETE = "/delete/{id}";
	private static final String UPDATE_BY_ID = "/update/{id}";
	private static final String USER_BY_NAME = "/user/byname/{name}";
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value=HEALTH)
	public String getHealth() {
		return "GET OK";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value=ALL)
	@CrossOrigin(origins= {"http://127.0.0.1:9000","http://127.0.0.1:8080"})
	public ResponseEntity<Response> getListUsers() {
		
		Response response = new Response<>();		
		List<User> users = userService.getListUsers();
		
		if(users.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		response.setData(users);		
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping(value=USER_BY_NAME)
	@CrossOrigin(origins= {"http://127.0.0.1:9000","http://127.0.0.1:8080"})
	public ResponseEntity<Response<User>> getUserByUserName(@PathVariable("name") String userName) {
		
		Response response = new Response<>();	
		
		User user = userService.findByUserName(userName);
		
		if(user==null) {
			return ResponseEntity.notFound().build();
		}
		
		response.setData(user);		
		return ResponseEntity.ok(response);
		
	}
	
	@PostMapping(value=INSERT)
	@CrossOrigin(origins= {"http://127.0.0.1:9000","http://127.0.0.1:8080"})
	public ResponseEntity<Response<User>> insertUser(@Valid @RequestBody User user, BindingResult result) {
		
		Response<User> response = new Response<User>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(erros -> response.getErrors().add(erros.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		user = userService.insert(user);
		response.setData(user);
				
		return ResponseEntity.ok(response);    
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping(value = DELETE)
	@CrossOrigin(origins= {"http://127.0.0.1:9000","http://127.0.0.1:8080"})
	public ResponseEntity<Response> deleteUser(@PathVariable("id") String userId) {
				
		Response<Object> response = new Response<Object>();	
 
        boolean deleted = userService.deleteUserById(userId);
        
        if (deleted == false) {
          System.out.println("Unable to delete. User with id " + userId + " not found");
          return ResponseEntity.notFound().build();
        }        
        
        response.setData(deleted);
        return ResponseEntity.ok(response);	
	}
	
	@PutMapping(value = UPDATE_BY_ID)
	@CrossOrigin(origins= {"http://127.0.0.1:9000","http://127.0.0.1:8080"})
    public ResponseEntity<User> updateUser(@PathVariable("id") String userId, @RequestBody User user) {
        System.out.println("Updating User " + userId);
          
        User currentUser = userService.findById(userId);
          
        if (currentUser==null) {
            System.out.println("User with id " + userId + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
  
        currentUser.setUserName(user.getUserName());
        currentUser.setEmail(user.getEmail());
          
        userService.updateUser(currentUser);
        return ResponseEntity.ok(currentUser);
    }

}
