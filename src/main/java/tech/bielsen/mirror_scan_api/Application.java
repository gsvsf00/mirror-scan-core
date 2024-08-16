package tech.bielsen.mirror_scan_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tech.bielsen.mirror_scan_api.model.ApplicationUser;
import tech.bielsen.mirror_scan_api.model.Role;
import tech.bielsen.mirror_scan_api.repository.RoleRepository;
import tech.bielsen.mirror_scan_api.repository.UserRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepo, UserRepository userRepo) {
		return args -> {
			if (roleRepo.findByAuthority("ROLE_USER") == null) {
				Role r = new Role();
				r.setAuthority("ROLE_USER");
				roleRepo.save(r);
			}

			ApplicationUser u = new ApplicationUser();
			u.setUsername("test");
			u.setPassword("test");
			u.setEmail("teste@gmail.com");

			userRepo.save(u);
		};
	}
}
