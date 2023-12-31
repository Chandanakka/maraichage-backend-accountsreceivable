package net.maraichage.maraichagebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "")
@EnableAutoConfiguration

@SpringBootApplication
public class MaraichageBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaraichageBackendApplication.class, args);
	}

}
