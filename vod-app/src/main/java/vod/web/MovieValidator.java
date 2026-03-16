package vod.web;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vod.service.MovieService;
import vod.web.dto.MovieDTO;

@Component
public class MovieValidator implements Validator {

    private final MovieService movieService;

    public MovieValidator(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MovieDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MovieDTO dto = (MovieDTO) target;
        if (dto.getDirectorId() == null || movieService.getDirectorById(dto.getDirectorId()) == null) {
            errors.reject("movie.director.missing");
        }
    }
}
