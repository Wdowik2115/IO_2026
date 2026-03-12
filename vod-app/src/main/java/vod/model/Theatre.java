package vod.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class Theatre {

    private int id;

    @NotNull
    @Size(min = 2, max = 20)
    private String name;

    @NotNull
    @Size(min = 2, max = 100)
    private String logo;

    @JsonIgnore
    private List<Movie> movies = new ArrayList<>();

    public Theatre(int id, String name, String logo) {
        this.id = id;
        this.name = name;
        this.logo = logo;
    }

    public Theatre() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }
    public List<Movie> getMovies() { return movies; }
    public void setMovies(List<Movie> movies) { this.movies = movies; }
    public void addMovie(Movie m) { this.movies.add(m); }

    @Override
    public String toString() {
        return "Theatre{name='" + name + "', logo='" + logo + "'}";
    }
}
