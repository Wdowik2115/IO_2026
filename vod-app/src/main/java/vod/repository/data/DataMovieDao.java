package vod.repository.data;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Theatre;
import vod.model.Director;
import vod.model.Movie;
import vod.repository.MovieDao;

import java.util.List;

@Repository
@Primary
public class DataMovieDao implements MovieDao {

    private final MovieRepository repository;

    public DataMovieDao(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Movie> findAll() {
        return repository.findAll();
    }

    @Override
    public Movie findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Movie> findByDirector(Director d) {
        return repository.findByDirector(d);
    }

    @Override
    public List<Movie> findByTheatre(Theatre t) {
        return repository.findByTheatre(t);
    }

    @Override
    public Movie add(Movie m) {
        return repository.save(m);
    }
}
