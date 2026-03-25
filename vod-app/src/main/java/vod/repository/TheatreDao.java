package vod.repository;

import vod.model.Theatre;
import vod.model.Movie;
import java.util.List;

public interface TheatreDao {
    List<Theatre> findAll();
    Theatre findById(int id);
    List<Theatre> findByMovie(Movie m);
    Theatre save(Theatre theatre);
}
