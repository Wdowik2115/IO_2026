package vod.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vod.model.Movie;
import vod.model.Theatre;
import vod.service.MovieService;
import vod.service.TheatreService;
import vod.web.dto.MovieDTO;

import java.util.List;

@RestController
@RequestMapping("webapi")
public class MovieRest {

    private static final Logger log = LoggerFactory.getLogger(MovieRest.class);

    private final MovieService movieService;
    private final TheatreService theatreService;

    public MovieRest(MovieService movieService, TheatreService theatreService) {
        this.movieService = movieService;
        this.theatreService = theatreService;
    }

    // GET /webapi/movies — wszystkie filmy
    @GetMapping("movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        log.info("GET /webapi/movies");
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    // GET /webapi/movies/{id} — film po ID
    @GetMapping("movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id) {
        log.info("GET /webapi/movies/{}", id);
        Movie movie = movieService.getMovieById(id);
        if (movie != null) return ResponseEntity.ok(movie);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // GET /webapi/Theatres/{TheatreId}/movies — filmy w danym kinie
    // (ten sam endpoint co w TheatreRest — możesz zostawić tylko w jednym miejscu)

    // POST /webapi/movies — dodaj nowy film
    @PostMapping("movies")
    public ResponseEntity<?> addMovie(
            @Validated @RequestBody MovieDTO movieDTO,
            Errors errors) {
        log.info("POST /webapi/movies, body: {}", movieDTO);
        if (errors.hasErrors()) {
            String errorMessages = errors.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .reduce("Errors:", (acc, msg) -> acc + " | " + msg);
            return ResponseEntity.badRequest().body(errorMessages);
        }
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setPoster(movieDTO.getPoster());
        movie.setRating((float) movieDTO.getRating());
        movie.setDirector(movieService.getDirectorById(movieDTO.getDirectorId()));

        Movie saved = movieService.addMovie(movie);
        log.info("New movie added: {}", saved);
        return ResponseEntity
                .created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequestUri()
                                .path("/{id}")
                                .buildAndExpand(saved.getId())
                                .toUri()
                )
                .body(saved);
    }
}
