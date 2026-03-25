package vod.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Theatre;
import vod.model.Director;
import vod.model.Movie;
import vod.repository.MovieDao;

import java.util.List;

@Repository
public class JpaMovieDao implements MovieDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Movie> findAll() {
        return entityManager.createQuery("select m from Movie m", Movie.class).getResultList();
    }

    @Override
    public Movie findById(int id) {
        return entityManager.find(Movie.class, id);
    }

    @Override
    public List<Movie> findByDirector(Director d) {
        return entityManager.createQuery("select m from Movie m where m.director=:director", Movie.class)
                .setParameter("director", d)
                .getResultList();
    }

    @Override
    public List<Movie> findByTheatre(Theatre t) {
        return entityManager.createQuery("select m from Movie m inner join m.theatres theatre where theatre=:theatre", Movie.class)
                .setParameter("theatre", t)
                .getResultList();
    }

    @Override
    public Movie add(Movie m) {
        entityManager.persist(m);
        return m;
    }
}
