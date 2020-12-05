package edu.mccneb.jakejackie.Movie2.DTO;


import edu.mccneb.jakejackie.Movie2.model.Movie;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class GenreDTO {
    private Long id;

    @NotNull
    private String genre;
    private Set<MovieDTO> movies = new HashSet<>();

    public GenreDTO() {}
    public GenreDTO(Long id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public Set<MovieDTO> getMovies() {
        return movies;
    }


    public void setMovies(Set<MovieDTO> movies) {
        this.movies = movies;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreDTO genreDTO = (GenreDTO) o;
        return Objects.equals(genre, genreDTO.genre);
    }


    @Override
    public int hashCode() {
        return Objects.hash(genre);
    }
}
