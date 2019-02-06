package springbootsample;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import springbootsample.controller.UserController;
import springbootsample.model.User;
import springbootsample.model.UserBuilder;
import springbootsample.repository.UserRepository;
import springbootsample.service.UserService;
import springbootsample.service.UserServiceImpl;


@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(UserController.class)
public class testSampleController {
	
	@Autowired
	private MockMvc mvc;
	
	@Mock
    UserService userServiceMock;
	
	@InjectMocks
	UserController userController;
    
    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    	mvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
    }
	
	@Test
	public void givenUsers_whenGetUsers_thenReturnJsonArray() throws Exception {	
		
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
				  	
    	List<User> users = Arrays.asList(user_1,user_2);
    	
		when(userServiceMock.getListUsers()).thenReturn(users);
		
		mvc.perform(get("/sample/all").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.data[0].userId", is("5c4a2076b834740001363267")))
		.andExpect(jsonPath("$.data[1].userName",  is("tedyfiromi")));

	}
	
	
	@Test
	public void givenUser_whenDeteteUser_thenReturnStatusOk() throws Exception {
		
		User user = new UserBuilder()
				.id("5c4a2076b834740001363267")
				.name("marcosmiranda")
				.email("marcosfiromi@outlook.com.br")
				.build();
		
		when(userServiceMock.findById("5c4a2076b834740001363267")).thenReturn(user);
		when(userServiceMock.deleteUserById("5c4a2076b834740001363267")).thenReturn(true);

		
		mvc.perform(delete("/sample/delete/5c4a2076b834740001363267").contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		 verify(userServiceMock, times(1)).deleteUserById("5c4a2076b834740001363267");
	}
	
	@Test
	public void givenUser_whenUpdateUser_thenReturnJsonArrays() throws Exception {
		
		User user = new UserBuilder()
				.id("5c4a2076b834740001363267")
				.name("marcosmiranda")
				.email("marcosfiromi@outlook.com.br")
				.build();
		
		when(userServiceMock.findById("5c4a2076b834740001363267")).thenReturn(user);
		
		mvc.perform(put("/sample/update/5c4a2076b834740001363267").contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		
	}

}
