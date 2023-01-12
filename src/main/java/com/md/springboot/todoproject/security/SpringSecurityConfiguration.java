package com.md.springboot.todoproject.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/*
 * @Author Maulik Davra
 * This class contains number of configuration of beans
 * In this class we will create number of beans and will return them accordingly
 * 
 * To save credentials we normally make use LDAP or Database
 * 
 */
@Configuration
public class SpringSecurityConfiguration {
	
	//To keep things for now, we will make use of In Memory
	
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		
		
		
		UserDetails userDetails = createNewUser("Md06", "Md123");
		UserDetails userDetails2 = createNewUser("Md07", "Md123");
		UserDetails userDetails3 = createNewUser("Md08", "Md123");

		
		return new InMemoryUserDetailsManager(userDetails, userDetails2, userDetails3);
	}

	private UserDetails createNewUser(String username, String password) {
		
		Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
		
		UserDetails userDetails = User.builder()
									.passwordEncoder(passwordEncoder)
									.username(username)
									.password(password)
									.roles("USER","ADMIN")
									.build();
		return userDetails;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
