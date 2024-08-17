package tech.bielsen.mirror_scan_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tech.bielsen.mirror_scan_api.model.ApplicationUser;
import tech.bielsen.mirror_scan_api.repository.UserRepository;

import java.time.LocalDateTime;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner run(UserRepository userRepo) {
		return args -> {

			if (userRepo.findByUsername("test").isEmpty()) {
				ApplicationUser user = new ApplicationUser();
				user.setUsername("test");
				user.setPassword("test");
				user.setEmail("test@gmail.com");
				user.setDisplayName("Test User");
				user.setCreated(LocalDateTime.now());
				userRepo.save(user);
			}
			else {
				System.out.println("User already exists");
			}
		};
	}
}
