package vod.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class Movie {

    private int id;

    @NotBlank
    private String title;

    private String poster;

    private Director director;

    private float rating;

    @JsonIgnore
    private List<Theatre> theatres = new ArrayList<>();

    public Movie(int id, String title, String poster, Director director, float rating) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.director = director;
        this.rating = rating;
    }

    public Movie() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }
    public Director getDirector() { return director; }
    public void setDirector(Director director) { this.director = director; }
    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }
    public List<Theatre> getTheatres() { return theatres; }
    public void setTheatres(List<Theatre> theatres) { this.theatres = theatres; }
    public void addTheatre(Theatre t) { this.theatres.add(t); }

    @Override
    public String toString() {
        return "Movie{title='" + title + "', director=" + director + ", rating=" + rating + "}";
    }
}
