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
        Theatre Theatre = (Theatre) target;
        boolean duplicate = theatreService.getAllTheatres().stream()
                .anyMatch(t -> t.getName().equalsIgnoreCase(Theatre.getName()));
        if (duplicate) {
            errors.reject("Theatre.duplicate");
        }
    }
}
