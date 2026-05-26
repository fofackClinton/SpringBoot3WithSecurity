package com.AppJoke.joke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JokeApplication {
	// @Autowired
	// UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(JokeApplication.class, args);
	}
	// @PostConstruct
	// void init_users() {
	// 	//ajouter les rôles
	// 	userService.addRole(new Role(null,"ADMIN"));
	// 	userService.addRole(new Role(null,"USER"));
		
	// 	//ajouter les users
	// 	userService.saveUser(new User(null,"admin","123",true,null));
	// 	userService.saveUser(new User(null,"nadhem","123",true,null));
	// 	userService.saveUser(new User(null,"yassine","123",true,null));
		
	// 	//ajouter les rôles aux users
	// 	userService.addRoleToUser("admin", "ADMIN");
	// 	userService.addRoleToUser("admin", "USER");
		
	// 	userService.addRoleToUser("nadhem", "USER");
	// 	userService.addRoleToUser("yassine", "USER");		
	// } 
	


}
