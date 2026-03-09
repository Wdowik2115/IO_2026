package vod.service.impl;

import org.springframework.stereotype.Service;
import vod.model.Director;
import vod.model.Movie;
import vod.repository.DirectorDao;
import vod.repository.MovieDao;
import vod.repository.TheatreDao;
import vod.service.MovieService;

import java.util.List;
import java.util.logging.Logger;

@Service
public class MovieServiceBean implements MovieService {

    private static final Logger log = Logger.getLogger(MovieService.class.getName());

    private DirectorDao directorDao;
    private TheatreDao theatreDao;
    private MovieDao movieDao;

    public MovieServiceBean(DirectorDao directorDao, TheatreDao theatreDao, MovieDao movieDao) {
        this.directorDao = directorDao;
        this.theatreDao = theatreDao;
        this.movieDao = movieDao;
    }

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

    @Override
    public Movie addMovie(Movie m) {
        log.info("about to add movie " + m);
        return movieDao.add(m);
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

    @Override
    public Director addDirector(Director d) {
        log.info("about to add director " + d);
        return directorDao.add(d);
    }
}
