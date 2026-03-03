package vod.repository.mem;

import org.springframework.stereotype.Component;
import vod.model.Movie;
import vod.model.Theatre;
import vod.repository.TheatreDao;

import java.util.List;

@Component
public class DummyTheatreDao implements TheatreDao {

    @Override
    public List<Theatre> findAll() {
        return List.of(); // pusta lista
    }

    @Override
    public Theatre findById(int id) {
        return null;
    }

    @Override
    public List<Theatre> findByMovie(Movie m) {
        return List.of();
    }

    @Override
    public Theatre add(Theatre t) {
        return null;
    }
}
