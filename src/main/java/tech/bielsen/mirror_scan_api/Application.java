package tech.bielsen.mirror_scan_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tech.bielsen.mirror_scan_api.model.ApplicationUser;
import tech.bielsen.mirror_scan_api.model.ScanMirror;
import tech.bielsen.mirror_scan_api.repository.ScanRepository;
import tech.bielsen.mirror_scan_api.repository.UserRepository;

import java.time.LocalDateTime;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


//	@Bean
//	CommandLineRunner run(UserRepository userRepo) {
//		return args -> {
//
//			if (userRepo.findByUsername("test").isEmpty()) {
//				ApplicationUser user = new ApplicationUser();
//				user.setUsername("test");
//				user.setPassword("test");
//				user.setEmail("test@gmail.com");
//				user.setDisplayName("Test User");
//				user.setCreated(LocalDateTime.now());
//				userRepo.save(user);
//			}
//			else {
//				System.out.println("User already exists");
//			}
//		};
//	}

	@Bean
	CommandLineRunner run (ScanRepository scanRepository){
		return args -> {
			if(scanRepository.findByTitle("Logging 10,000 Years into the Future").isEmpty()){
				ScanMirror scanMirror = new ScanMirror();
				scanMirror.setImgUrl("https://gg.asuracomic.net/storage/media/58/conversions/49ab5c70-optimized.webp");
				scanMirror.setTitle("Logging 10,000 Years into the Future");
				scanMirror.setUrlPage("https://asuracomic.net/series/logging-10000-years-into-the-future-6e11b809");
				scanRepository.save(scanMirror);
			}
			else {
				System.out.println("Scan already exists");
			}
		};
	}
}
