package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import vod.model.Director;

public interface DirectorRepository extends JpaRepository<Director, Integer> {
}
