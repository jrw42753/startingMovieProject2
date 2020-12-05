package edu.mccneb.jakejackie.Movie2.service;

import edu.mccneb.jakejackie.Movie2.DTO.GenreDTO;
import edu.mccneb.jakejackie.Movie2.DTO.MovieDTO;
import edu.mccneb.jakejackie.Movie2.Repository.GenreRepository;
import edu.mccneb.jakejackie.Movie2.Repository.MovieRepository;
import edu.mccneb.jakejackie.Movie2.model.Genre;
import edu.mccneb.jakejackie.Movie2.model.Movie;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    @Transactional
    public Long addMovie(MovieDTO movieDTO) {
        List<String> genreStrs = new ArrayList<>();
        if (!movieDTO.getGenresStr().trim().isEmpty()) {
            String[] array = (movieDTO.getGenresStr().split(" "));
            for (String t : array) {
                genreStrs.add(t);
            }
        }
        return addMovie(movieDTO.getMovie(), genreStrs);
    }



    @Transactional
    public Long addMovie(String movieStr, List<String> genreStrs) {
        Movie movie = new Movie(movieStr);
        movie = movieRepository.save(movie);
        for (String genreStr : genreStrs) {
            Genre genre = null;
            Optional<Genre> optional = genreRepository.findByGenre(genreStr);
            genre = optional.isPresent() ? optional.get() : new Genre(genreStr);
            genre.addMovie(movie);
            genreRepository.save(genre);
        }
        return movie.getId();
    }



    @Transactional
    public List<MovieDTO> findAllMovies() {
        List<Movie> lazyMovies = new ArrayList<Movie>();
        movieRepository.findAll().forEach(lazyMovies::add);
        List<MovieDTO> loadedMovies = new ArrayList<>();
        for (Movie lu : lazyMovies) {
            MovieDTO movie = new MovieDTO(lu.getId(), lu.getMovie());
            copyGenres(lu, movie);
            loadedMovies.add(movie);
        }
        return loadedMovies;
    }



    private void copyGenres(Movie lu, MovieDTO movie) {
        for (Genre t : lu.getGenres()) {
            movie.getGenres().add(new GenreDTO(t.getId(), t.getGenre()));
        }
    }



    // commented out @Transactional to demo an error:

    @Transactional
    public MovieDTO findMovieById(Long id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        MovieDTO movieDto = null;
        if (optionalMovie.isPresent()) {
            Movie lazyMovie = optionalMovie.get();
            movieDto = new MovieDTO(lazyMovie.getId(), lazyMovie.getMovie());
            copyGenres(lazyMovie, movieDto);
        }
        return movieDto;

    }
}
