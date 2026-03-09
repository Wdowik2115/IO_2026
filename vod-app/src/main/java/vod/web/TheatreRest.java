package vod.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vod.model.Movie;
import vod.model.Theatre;
import vod.service.MovieService;
import vod.service.TheatreService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/webapi")
public class TheatreRest {

    private final TheatreService theatreService;
    private final MovieService movieService;

    // GET wszystkich teatrów, opcjonalnie filtrowanie po nazwie
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

    // GET jednego teatru po ID – 404 jeśli nie istnieje
    @GetMapping("/theatres/{id}")
    public ResponseEntity<Theatre> getTheatreById(@PathVariable("id") int id) {
        log.info("GET /webapi/theatres/{}", id);
        Theatre t = theatreService.getTheatreById(id);
        if (t == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(t);
    }

    // GET teatrów grających dany spektakl
    @GetMapping("/movies/{id}/theatres")
    public ResponseEntity<List<Theatre>> getTheatresByMovie(@PathVariable("id") int id) {
        log.info("GET /webapi/movies/{}/theatres", id);
        Movie movie = movieService.getMovieById(id);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(theatreService.getTheatresByMovie(movie));
    }

    // POST – dodanie nowego teatru, zwraca 201
    @PostMapping("/theatres")
    public ResponseEntity<Theatre> addTheatre(@RequestBody Theatre theatre) {
        log.info("POST /webapi/theatres, body={}", theatre);
        Theatre saved = theatreService.addTheatre(theatre);
        return ResponseEntity.status(201).body(saved);
    }
}
