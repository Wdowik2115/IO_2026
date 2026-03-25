package vod.repository.data;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Theatre;
import vod.model.Movie;
import vod.repository.TheatreDao;

import java.util.List;

@Repository
@Primary
public class DataTheatreDao implements TheatreDao {

    private final TheatreRepository repository;

    public DataTheatreDao(TheatreRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Theatre> findAll() {
        return repository.findAll();
    }

    @Override
    public Theatre findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Theatre> findByMovie(Movie m) {
        return repository.findByMovie(m);
    }

    @Override
    public Theatre save(Theatre theatre) {
        return repository.save(theatre);
    }
}
