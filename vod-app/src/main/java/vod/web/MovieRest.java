package vod.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("webapi")
public class MovieRest {

    private final MovieService movieService;
    private final TheatreService theatreService;

    // 1. GET /webapi/movies — wszystkie filmy
    @GetMapping("movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        log.info("GET /webapi/movies");
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    // 2. GET /webapi/movies/{id} — film po ID
    @GetMapping("movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id) {
        log.info("GET /webapi/movies/{}", id);
        Movie movie = movieService.getMovieById(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 3. GET /webapi/theatres/{theatreId}/movies — filmy grane w danym kinie
    @GetMapping("theatres/{theatreId}/movies")
    public ResponseEntity<List<Movie>> getMoviesInTheatre(@PathVariable int theatreId) {
        log.info("GET /webapi/theatres/{}/movies", theatreId);
        Theatre theatre = theatreService.getTheatreById(theatreId);
        if (theatre == null) {
            return ResponseEntity.notFound().build();
        }
        List<Movie> movies = theatreService.getMoviesInTheatre(theatre);
        log.info("{} movies found in theatre {}", movies.size(), theatre.getName());
        return ResponseEntity.ok(movies);
    }

    // 4. POST /webapi/movies — dodaj nowy film (z walidacją i DTO)
    @PostMapping("movies")
    public ResponseEntity<?> addMovie(@Validated @RequestBody MovieDTO movieDTO, Errors errors) {
        log.info("POST /webapi/movies, body: {}", movieDTO);
        if (errors.hasErrors()) {
            String errorMessages = errors.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .reduce("Errors: ", (acc, msg) -> acc + " | " + msg);
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
