package vod.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Director;
import vod.repository.DirectorDao;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Primary
public class DataDirectorDao implements DirectorDao {

    private final DirectorRepository repository;

    @Override
    public List<Director> findAll() {
        return repository.findAll();
    }

    @Override
    public Director findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Director add(Director d) {
        return repository.save(d);
    }
}
