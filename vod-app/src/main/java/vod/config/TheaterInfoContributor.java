package vod.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;
import vod.service.TheatreService;

@Component
@RequiredArgsConstructor
public class TheaterInfoContributor implements InfoContributor {

    private final TheatreService theatreService;

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("theaters", theatreService.getAllTheatres().size());
    }
}
