package vod.repository.mem;

import org.springframework.stereotype.Repository;
import vod.model.Director;
import vod.repository.DirectorDao;

import java.util.List;

public class MemDirectorDao implements DirectorDao {

    @Override
    public List<Director> findAll() {
        return SampleData.directors;
    }

    @Override
    public Director findById(int id) {
        return SampleData.directors.stream()
                .filter(d -> d.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public Director add(Director d) {
        int max = SampleData.directors.stream()
                .max((d1, d2) -> d1.getId() - d2.getId()).get().getId();
        d.setId(++max);
        SampleData.directors.add(d);
        return d;
    }
}
