package vod.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Theatre;
import vod.model.Movie;
import vod.repository.TheatreDao;

import java.util.List;

@Repository
public class JpaTheatreDao implements TheatreDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Theatre> findAll() {
        return entityManager.createQuery("select t from Theatre t", Theatre.class).getResultList();
    }

    @Override
    public Theatre findById(int id) {
        return entityManager.find(Theatre.class, id);
    }

    @Override
    public List<Theatre> findByMovie(Movie m) {
        return entityManager.createQuery("select t from Theatre t inner join t.movies movie where movie=:movie", Theatre.class)
                .setParameter("movie", m)
                .getResultList();
    }

    @Override
    public Theatre save(Theatre theatre) {
        entityManager.persist(theatre);
        return theatre;
    }
}
