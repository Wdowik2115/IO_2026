// VodAdvice.java (pełna zaktualizowana wersja)
package vod.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class VodAdvice {

    private final TheatreValidator TheatreValidator;
    private final MovieValidator movieValidator;

    public VodAdvice(TheatreValidator TheatreValidator, MovieValidator movieValidator) {
        this.TheatreValidator = TheatreValidator;
        this.movieValidator = movieValidator;
    }

    @InitBinder("Theatre")          // tylko dla obiektów "Theatre" w TheatreRest
    public void initTheatreBinder(WebDataBinder binder) {
        binder.addValidators(TheatreValidator);
    }

    @InitBinder("movieDTO")         // tylko dla obiektów "movieDTO" w MovieRest
    public void initMovieBinder(WebDataBinder binder) {
        binder.addValidators(movieValidator);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArg(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.LOOP_DETECTED).body(ex.getMessage());
    }
}
