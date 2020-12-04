package edu.mccneb.jakejackie.Movie2.Repository;

import edu.mccneb.jakejackie.Movie2.model.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository {

    public List<Movie> getAllMovies();
//    public List<Movie> findByName(String name);
//    public List<Movie> findByGenre(String genre);


}
