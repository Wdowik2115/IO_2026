package vod;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import vod.model.Theatre;
import vod.service.TheatreService;

import java.util.List;

@Component
public class VodComponent implements CommandLineRunner, ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = LoggerFactory.getLogger(VodComponent.class);

    private final TheatreService theatreService;

    public VodComponent(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

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

        List<Theatre> Theatres = theatreService.getAllTheatres();
        log.info("{} Theatres found:", Theatres.size());
        Theatres.forEach(t -> log.info("  {}", t));
    }
}
