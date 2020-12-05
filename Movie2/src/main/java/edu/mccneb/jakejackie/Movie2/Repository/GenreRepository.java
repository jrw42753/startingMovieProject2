package edu.mccneb.jakejackie.Movie2.Repository;

import edu.mccneb.jakejackie.Movie2.model.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Long> {


//    public List<Movie> getAllMovies();
//    public List<Movie> findByName(String name);
      Optional<Genre> findByGenre(String genreStr);


}
