package edu.mccneb.jakejackie.Movie2.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String movie;
//    private String genre;

    @ManyToMany(mappedBy="movies")
//    @JoinTable(name = "movie_genre",
//            joinColumns = @JoinColumn(name = "movie_id"),
//            inverseJoinColumns = @JoinColumn(name = "genre_id")
//    )
    private Set<Genre> genres = new HashSet<>();

    public Movie(){ movie = null; }
    public Movie(String name) {
        this.movie = movie;
    }

    public void addGenre(Genre genre) {
        this.genres.add(genre);
        genre.getMovies().add(this);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movie='" + movie + '\'' +
                ", genres=" + genres +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie1 = (Movie) o;
        return Objects.equals(movie, movie1.movie);
    }

    @Override
    public int hashCode() {
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

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }
}
