package vod.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vod.model.Theatre;
import vod.model.Director;
import vod.model.Movie;
import vod.repository.MovieDao;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Primary
public class DataMovieDao implements MovieDao {

    private final MovieRepository repository;

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

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public Movie add(Movie m) {
        return repository.save(m);
    }
}
