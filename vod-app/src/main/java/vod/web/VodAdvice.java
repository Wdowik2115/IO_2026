package vod.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
@RequiredArgsConstructor
public class VodAdvice {

    // Jeśli masz własne walidatory (np. CinemaValidator), wstrzyknij je tutaj
    // i zarejestruj poniżej. Na razie przykład bez własnego walidatora:

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // binder.addValidators(twojWalidator); // odkomentuj gdy dodasz własny walidator
    }
}
