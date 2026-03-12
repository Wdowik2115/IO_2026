package vod.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

public class Director {

    private int id;
    private String firstName;
    private String lastName;

    @JsonIgnore
    private List<Movie> movies = new ArrayList<>();

    public Director(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Director() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public List<Movie> getMovies() { return movies; }
    public void setMovies(List<Movie> movies) { this.movies = movies; }
    public void addMovie(Movie m) { this.movies.add(m); }

    @Override
    public String toString() {
        return "Director{id=" + id + ", firstName='" + firstName + "', lastName='" + lastName + "'}";
    }
}
