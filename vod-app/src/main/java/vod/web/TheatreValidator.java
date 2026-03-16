// vod/web/TheatreValidator.java
package vod.web;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vod.model.Theatre;
import vod.service.TheatreService;

@Component
public class TheatreValidator implements Validator {

    private final TheatreService theatreService;

    public TheatreValidator(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Theatre.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Theatre theatre = (Theatre) target;
        boolean duplicate = theatreService.getAllTheatres().stream()
                .anyMatch(t -> t.getName().equalsIgnoreCase(theatre.getName()));
        if (duplicate) {
            errors.reject("theatre.duplicate");
        }
    }
}
