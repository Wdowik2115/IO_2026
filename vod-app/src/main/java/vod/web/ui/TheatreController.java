package vod.web.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vod.model.Movie;
import vod.service.MovieService;
import vod.service.TheatreService;

@Controller
public class TheatreController {

    private final TheatreService theatreService;
    private final MovieService movieService;

    public TheatreController(TheatreService theatreService, MovieService movieService) {
        this.theatreService = theatreService;
        this.movieService = movieService;
    }
    @GetMapping("theatres-view")
    public String getTheatresView(
            @RequestParam(required = false) Integer movieId,
            Model model) {

        if (movieId != null) {
            var movie = movieService.getMovieById(movieId);
            model.addAttribute("theatres", theatreService.getTheatresByMovie(movie));
            model.addAttribute("title", "Teatry grające: " + movie.getTitle());
        } else {
            model.addAttribute("theatres", theatreService.getAllTheatres());
            model.addAttribute("title", "Wszystkie teatry");
        }
        return "theatresView";
    }
}
