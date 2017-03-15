package edu.pl.pollub;

import edu.pl.pollub.config.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class JustMemesApplication {

	public static void main(String[] args) {
		SpringApplication.run(JustMemesApplication.class, args);
	}


}
