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
import vod.model.Movie;
import vod.model.Theatre;
import vod.service.MovieService;
import vod.service.TheatreService;

import java.util.List;
import java.util.Locale;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/webapi")
public class TheatreRest {

    private final TheatreService theatreService;
    private final MovieService movieService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    @GetMapping("/theatres")
    public List<Theatre> getAllTheatres(
            @RequestParam(required = false) String name,
            @RequestHeader(value = "X-Client-Id", required = false) String clientId) {
        log.info("GET /webapi/theatres, name={}, X-Client-Id={}", name, clientId);
        if (name != null) {
            return theatreService.getAllTheatres().stream()
                    .filter(t -> t.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        }
        return theatreService.getAllTheatres();
    }

    @GetMapping("/theatres/{id}")
    public ResponseEntity<Theatre> getTheatreById(@PathVariable("id") int id) {
        log.info("GET /webapi/theatres/{}", id);
        Theatre t = theatreService.getTheatreById(id);
        if (t == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(t);
    }

    @GetMapping("/movies/{id}/theatres")
    public ResponseEntity<List<Theatre>> getTheatresByMovie(@PathVariable("id") int id) {
        log.info("GET /webapi/movies/{}/theatres", id);
        Movie movie = movieService.getMovieById(id);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(theatreService.getTheatresByMovie(movie));
    }

    @PostMapping("/theatres")
    public ResponseEntity<?> addTheatre(
            @Validated @RequestBody Theatre theatre,
            Errors errors,
            HttpServletRequest request) {

        log.info("POST /webapi/theatres, body={}", theatre);

        if (errors.hasErrors()) {
            Locale locale = localeResolver.resolveLocale(request);
            String errorMessage = errors.getAllErrors().stream()
                    .map(oe -> messageSource.getMessage(oe.getCode(), null, locale))
                    .reduce("errors:\n", (accu, msg) -> accu + msg + "\n");
            return ResponseEntity.badRequest().body(errorMessage);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(theatreService.addTheatre(theatre));
    }
}
