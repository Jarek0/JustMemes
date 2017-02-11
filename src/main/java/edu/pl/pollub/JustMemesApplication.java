package edu.pl.pollub;

import edu.pl.pollub.services.FileService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JustMemesApplication {

	public static void main(String[] args) {
		SpringApplication.run(JustMemesApplication.class, args);
	}
	@Bean
	CommandLineRunner init(FileService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
