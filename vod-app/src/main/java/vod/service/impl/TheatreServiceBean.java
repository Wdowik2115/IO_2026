package vod.service.impl;

import org.springframework.stereotype.Component;
import vod.model.Movie;
import vod.model.Theatre;
import vod.repository.MovieDao;
import vod.repository.TheatreDao;
import vod.service.TheatreService;

import java.util.List;
import java.util.logging.Logger;

@Component
public class TheatreServiceBean implements TheatreService {

    private static final Logger log = Logger.getLogger(TheatreService.class.getName());

    private TheatreDao theatreDao;
    private MovieDao movieDao;

    public TheatreServiceBean(TheatreDao theatreDao, MovieDao movieDao) {
        log.info("creating theatre service bean");
        this.theatreDao = theatreDao;
        this.movieDao = movieDao;
    }

    @Override
    public Theatre getTheatreById(int id) {
        log.info("searching theatre by id " + id);
        return theatreDao.findById(id);
    }

    @Override
    public List<Theatre> getAllTheatres() {
        log.info("searching all theatres");
        return theatreDao.findAll();
    }

    @Override
    public List<Theatre> getTheatresByMovie(Movie m) {
        log.info("searching theatres by movie " + m.getId());
        return theatreDao.findByMovie(m);
    }

    @Override
    public List<Movie> getMoviesInTheatre(Theatre t) {
        log.info("searching movies in theatre " + t.getId());
        return movieDao.findByTheatre(t);
    }
}
