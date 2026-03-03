package vod.repository;

import vod.model.Movie;
import vod.model.Theatre;
import java.util.List;

public interface TheatreDao {
    List<Theatre> findAll();
    Theatre findById(int id);
    List<Theatre> findByMovie(Movie m);
    Theatre add(Theatre t);
}
