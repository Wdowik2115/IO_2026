package vod.repository.mem;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import vod.model.Movie;
import vod.model.Theatre;
import vod.repository.TheatreDao;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
public class MemTheatreDao implements TheatreDao {

    @Override
    public List<Theatre> findAll() {
        return SampleData.theatres;
    }

    @Override
    public Theatre findById(int id) {
        return SampleData.theatres.stream()
                .filter(t -> t.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public List<Theatre> findByMovie(Movie m) {
        return SampleData.theatres.stream()
                .filter(t -> t.getMovies().contains(m))
                .collect(Collectors.toList());
    }

    @Override
    public Theatre add(Theatre t) {
        int max = SampleData.theatres.stream()
                .max((t1, t2) -> t1.getId() - t2.getId()).get().getId();
        t.setId(++max);
        SampleData.theatres.add(t);
        return t;
    }
}
