package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vod.model.Theatre;
import vod.model.Movie;

import java.util.List;

public interface TheatreRepository extends JpaRepository<Theatre, Integer> {

    @Query("select t from Theatre t inner join t.movies movie where movie=:movie")
    List<Theatre> findByMovie(@Param("movie") Movie movie);
}

