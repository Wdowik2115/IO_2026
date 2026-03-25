package vod.repository;

import vod.model.Theatre;
import vod.model.Director;
import vod.model.Movie;
import java.util.List;

public interface MovieDao {
    List<Movie> findAll();
    Movie findById(int id);
    List<Movie> findByDirector(Director d);
    List<Movie> findByTheatre(Theatre c);
    Movie add(Movie m);
}
