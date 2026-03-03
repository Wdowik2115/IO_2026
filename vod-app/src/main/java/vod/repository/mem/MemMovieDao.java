package vod.repository.mem;

import org.springframework.stereotype.Component;
import vod.model.Director;
import vod.model.Movie;
import vod.model.Theatre;
import vod.repository.MovieDao;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemMovieDao implements MovieDao {

    @Override
    public List<Movie> findAll() {
        return SampleData.movies;
    }

    @Override
    public Movie findById(int id) {
        return SampleData.movies.stream()
                .filter(m -> m.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public List<Movie> findByDirector(Director d) {
        return SampleData.movies.stream()
                .filter(m -> m.getDirector() == d)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> findByTheatre(Theatre t) {
        return SampleData.movies.stream()
                .filter(m -> m.getTheatres().contains(t))
                .collect(Collectors.toList());
    }

    @Override
    public Movie add(Movie m) {
        int max = SampleData.movies.stream()
                .max((m1, m2) -> m1.getId() - m2.getId()).get().getId();
        m.setId(++max);
        SampleData.movies.add(m);
        return m;
    }
}
