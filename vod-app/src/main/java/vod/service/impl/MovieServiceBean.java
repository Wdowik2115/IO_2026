package vod.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vod.model.Director;
import vod.model.Movie;
import vod.repository.DirectorDao;
import vod.repository.MovieDao;
import vod.repository.TheatreDao;
import vod.service.MovieService;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class MovieServiceBean implements MovieService {

    private static final Logger log = Logger.getLogger(MovieService.class.getName());

    private final DirectorDao directorDao;
    private final TheatreDao theatreDao;
    private final MovieDao movieDao;

    @Override
    public List<Movie> getAllMovies() {
        log.info("searching all movies...");
        return movieDao.findAll();
    }

    @Override
    public List<Movie> getMoviesByDirector(Director d) {
        log.info("searching movies by director " + d.getId());
        return movieDao.findByDirector(d);
    }

    @Override
    public Movie getMovieById(int id) {
        log.info("searching movie by id " + id);
        return movieDao.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Movie addMovie(Movie m) {
        log.info("about to add movie " + m);
        m = movieDao.add(m);
        if (m.getTitle().equals("Dziady")) {
            throw new RuntimeException("not yet!");
        }
        return m;
    }

    @Override
    public List<Director> getAllDirectors() {
        log.info("searching all directors");
        return directorDao.findAll();
    }

    @Override
    public Director getDirectorById(int id) {
        log.info("searching director by id " + id);
        return directorDao.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public Director addDirector(Director d) {
        log.info("about to add director " + d);
        return directorDao.add(d);
    }
}
