package com.example.javaUSSD;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JavaUssdApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaUssdApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository){

		return  args -> {

			User nick = new User("Nickson", " Kipkorir", "nick@gmail.com", "34096442");
			userRepository.save(nick);
		};
	}

}
