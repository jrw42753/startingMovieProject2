package edu.mccneb.jakejackie.Movie2;

import edu.mccneb.jakejackie.Movie2.Repository.MovieRepository;
import edu.mccneb.jakejackie.Movie2.model.Movie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class Movie2Application {

	public static void main(String[] args) {
		SpringApplication.run(Movie2Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(MovieRepository repository) {

		return (args) -> {
			// save a few Products
			repository.save(new Movie("Forrest Gump"));
			repository.save(new Movie("Titanic"));
		};
	}

}
