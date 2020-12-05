package edu.mccneb.jakejackie.Movie2.DTO;

import edu.mccneb.jakejackie.Movie2.model.Movie;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MovieDTO {

    private Long id;


    @NotNull
    private String movie;
    private String genresStr;
    private Set<GenreDTO> genres = new HashSet<>();

    public MovieDTO() {}
    public MovieDTO(Long id, String movie) {
       this.id = id;
       this.movie = movie;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDTO movieDTO = (MovieDTO) o;
        return Objects.equals(movie, movieDTO.movie);
    }

    @Override
    public int hashCode(){
        return Objects.hash(movie);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public Set<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreDTO> genres) {
        this.genres = genres;
    }

    public String getGenresStr() {
        return genresStr;
    }

    public void setGenresStr(String genresStr) {
        this.genresStr = genresStr;
    }

}
