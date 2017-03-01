package edu.pl.pollub;

import edu.pl.pollub.repository.MemRepository;
import edu.pl.pollub.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class JustMemesApplication {

	public static void main(String[] args) {
		SpringApplication.run(JustMemesApplication.class, args);
	}


}
