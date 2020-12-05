package edu.mccneb.jakejackie.Movie2;

import edu.mccneb.jakejackie.Movie2.Repository.GenreRepository;
import edu.mccneb.jakejackie.Movie2.Repository.MovieRepository;
import edu.mccneb.jakejackie.Movie2.model.Genre;
import edu.mccneb.jakejackie.Movie2.model.Movie;
import edu.mccneb.jakejackie.Movie2.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Movie2Application implements CommandLineRunner {

	@Autowired
	private MovieService movieService;

	public static void main(String[] args) {
		SpringApplication.run(Movie2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		processFileAddMovieSaveAsWeGo("src/main/resources/sample.data");
	}

	private void processFileAddMovieSaveAsWeGo(String fileName) throws IOException {
		List<String> list = getLinesFromFile(fileName);
		for (int i = 0; i < list.size(); i += 2) {
			String movieStr = list.get(i);
			List<String> genreList = new ArrayList<>();
			for (String str: list.get(i+1).split(" ")) {
				genreList.add(str);
			}
			movieService.addMovie(movieStr,genreList);
		}
	}

	private List<String> getLinesFromFile(String fileName) throws IOException {

		List<String> list = Files.readAllLines(new File(fileName).toPath(), Charset.defaultCharset());

		list = list.stream().filter(line -> line.trim().length() > 0).collect(Collectors.toList());

		return list;

	}
//	@Bean
//	public CommandLineRunner demo(MovieRepository movieRepository, GenreRepository genreRepository) {
//
//		return (args) -> {
//			// save a few Products
//			movieRepository.save(new Movie("Forrest Gump"));
//			movieRepository.save(new Movie("Titanic"));
//			genreRepository.save(new Genre("Drama"));
//		};
//	}

}
