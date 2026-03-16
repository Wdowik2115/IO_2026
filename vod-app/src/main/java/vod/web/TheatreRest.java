package vod.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vod.model.Movie;
import vod.model.Theatre;
import vod.service.MovieService;
import vod.service.TheatreService;

import java.util.List;
import java.util.Locale;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("webapi")
public class TheatreRest {

    private final TheatreService theatreService;
    private final MovieService movieService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    // GET /webapi/theatres — wszystkie kina (opcjonalnie filtrowane po nazwie)
    @GetMapping("theatres")
    public List<Theatre> getAllTheatres(
            @RequestParam(required = false) String name,
            @RequestHeader(value = "X-Client-Id", required = false) String clientId) {
        log.info("GET /webapi/theatres, name={}, X-Client-Id={}", name, clientId);

        if ("foo".equals(name)) {
            throw new RuntimeException("Illegal search phrase: foo");
        }

        if (name != null) {
            return theatreService.getAllTheatres().stream()
                    .filter(t -> t.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        }
        return theatreService.getAllTheatres();
    }

    // GET /webapi/theatres/{id} — kino po ID
    @GetMapping("theatres/{id}")
    public ResponseEntity<Theatre> getTheatreById(@PathVariable int id) {
        log.info("GET /webapi/theatres/{}", id);
        Theatre t = theatreService.getTheatreById(id);
        if (t == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(t);
    }

    // GET /webapi/theatres/{id}/movies — filmy grane w danym kinie
    @GetMapping("theatres/{id}/movies")
    public ResponseEntity<List<Movie>> getMoviesInTheatre(@PathVariable int id) {
        log.info("GET /webapi/theatres/{}/movies", id);
        Theatre theatre = theatreService.getTheatreById(id);
        if (theatre == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(theatreService.getMoviesInTheatre(theatre));
    }

    // GET /webapi/movies/{id}/theatres — kina w których grany jest dany film
    @GetMapping("movies/{id}/theatres")
    public ResponseEntity<List<Theatre>> getTheatresByMovie(@PathVariable int id) {
        log.info("GET /webapi/movies/{}/theatres", id);
        Movie movie = movieService.getMovieById(id);
        if (movie == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(theatreService.getTheatresByMovie(movie));
    }

    // POST /webapi/theatres — dodaj nowe kino
    @PostMapping("theatres")
    public ResponseEntity<?> addTheatre(
            @Validated @RequestBody Theatre theatre,
            Errors errors,
            HttpServletRequest request) {
        log.info("POST /webapi/theatres, body: {}", theatre);
        if (errors.hasErrors()) {
            Locale locale = localeResolver.resolveLocale(request);
            String errorMessage = errors.getAllErrors().stream()
                    .map(e -> messageSource.getMessage(e.getCode(), null, locale))
                    .reduce("Errors:", (acc, msg) -> acc + " | " + msg);
            return ResponseEntity.badRequest().body(errorMessage);
        }
        Theatre saved = theatreService.addTheatre(theatre);
        log.info("New theatre added: {}", saved);
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
