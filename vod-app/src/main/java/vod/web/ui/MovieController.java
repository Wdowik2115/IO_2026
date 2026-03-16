package vod.web.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vod.service.MovieService;
import vod.service.TheatreService;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final TheatreService theatreService;

    @GetMapping("movies-view")
    public String getMoviesView(
            @RequestParam(required = false) Integer theatreId,
            @RequestParam(required = false) Integer directorId,
            Model model) {

        if (theatreId != null) {
            var theatre = theatreService.getTheatreById(theatreId);
            model.addAttribute("movies", theatreService.getMoviesInTheatre(theatre));
            model.addAttribute("title", "Spektakle w teatrze: " + theatre.getName());
        } else if (directorId != null) {
            var director = movieService.getDirectorById(directorId);
            model.addAttribute("movies", movieService.getMoviesByDirector(director));
            model.addAttribute("title", "Spektakle reżysera: " + director.getFirstName() + " " + director.getLastName());
        } else {
            model.addAttribute("movies", movieService.getAllMovies());
            model.addAttribute("title", "Wszystkie spektakle");
        }
        return "moviesView";
    }
}
