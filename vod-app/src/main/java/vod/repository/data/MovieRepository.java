package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vod.model.Theatre;
import vod.model.Director;
import vod.model.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query("select m from Movie m where m.director=:director")
    List<Movie> findByDirector(@Param("director") Director director);

    @Query("select m from Movie m inner join m.theatres theatre where theatre=:theatre")
    List<Movie> findByTheatre(@Param("theatre") Theatre theatre);
}
