package vod;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import vod.model.Theatre;
import vod.service.TheatreService;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class VodComponent implements CommandLineRunner, ApplicationListener<ContextRefreshedEvent> {

    private final TheatreService theatreService;

    @PostConstruct
    public void init() {
        log.info(">>> @PostConstruct: bean skonstruowany, zależności wstrzyknięte");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info(">>> ApplicationListener: kontekst odświeżony");
    }

    @Override
    public void run(String... args) {
        log.info(">>> CommandLineRunner.run: aplikacja uruchomiona");

        List<Theatre> theatres = theatreService.getAllTheatres();
        log.info("{} theatres found:", theatres.size());
        theatres.forEach(t -> log.info("  {}", t));
    }
}
