package springbootsample;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import springbootsample.controller.UserController;
import springbootsample.model.User;
import springbootsample.model.UserBuilder;
import springbootsample.repository.UserRepository;
import springbootsample.service.UserServiceImpl;


@RunWith(MockitoJUnitRunner.Silent.class)
@WebMvcTest(UserController.class)
public class testSampleService {

	@Mock
    UserRepository userRepositoryMock;
    
    @InjectMocks
    UserServiceImpl userServiceMock;
    
    @Test
    public void testSizeListUser() throws Exception {
    	
    	User user_1 = new UserBuilder()
    			.id("5c4a2076b834740001363267")
    			.name("marcosmiranda")
    			.email("marcosfiromi@outlook.com.br")
    			.build();
    	
    	User user_2 = new UserBuilder()
    			.id("5c4a2173b834740001363268")
    			.name("tedyfiromi")
    			.email("tedyfiormi@hotmail.com.br")
    			.build();
    	  	
    	List<User> user = Arrays.asList(user_1,user_2);
        
    	when(userRepositoryMock.getListUsers()).thenReturn(user);
        assertEquals(2, userServiceMock.getListUsers().size());
    }
    
    @Test
    public void testUserFindById() {
    	
    	User user_find = new UserBuilder()
    			.id("5c4eefcdb834740001363269")
    			.name("tedymiranda")
    			.email("tedyfiromi@gmail.com")
    			.build();
    	
    	when(userRepositoryMock.findById("5c4a2076b834740001363267")).thenReturn(user_find);
        assertEquals(user_find, userServiceMock.findById("5c4a2076b834740001363267"));        
    }
    
    @Test
    public void testInsertUser() {
    	
    	User user_insert = new UserBuilder()
    			.id("5c4eefcdb834740001363269")
    			.name("tedymiranda")
    			.email("tedyfiromi@gmail.com")
    			.build();
    	
    	when(userRepositoryMock.insert(user_insert)).thenReturn(user_insert);
    	assertEquals("tedymiranda", userServiceMock.insert(user_insert).getUserName());
    	
    }
    
    @Test
    public void testUpdateUser() {
    	
    	User user_update = new UserBuilder()
    			.id("5c4eefcdb834740001363269")
    			.name("tedymiranda")
    			.email("tedyfiromi@gmail.com")
    			.build();
    	
    	when(userRepositoryMock.updateUser(user_update)).thenReturn(user_update);
    	assertEquals("tedyfiromi@gmail.com", userServiceMock.updateUser(user_update).getEmail());
    }
    
    @Test
    public void testDeleteUser() {
    	
    	when(userRepositoryMock.deleteUserById("5c4eefcdb834740001363269")).thenReturn(true);
    	assertEquals(true, userServiceMock.deleteUserById("5c4eefcdb834740001363269"));
    	
    }

}
