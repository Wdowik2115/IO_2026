package vod.service;

import vod.model.Movie;
import vod.model.Theatre;
import java.util.List;

public interface TheatreService {
    Theatre getTheatreById(int id);
    List<Theatre> getAllTheatres();
    List<Theatre> getTheatresByMovie(Movie m);
    List<Movie> getMoviesInTheatre(Theatre t);
}
