package vod.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vod.model.Theatre;
import vod.service.TheatreService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TheatreRest {

    private final TheatreService theatreService;

    @GetMapping("/theatres")
    public List<Theatre> getAllTheatres() {
        log.info("GET /theatres");
        return theatreService.getAllTheatres();
    }
}
