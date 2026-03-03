package vod.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VodConfig {

    @Bean
    public String foo() {
        return "Hello from Spring Bean - Theatre App!";
    }
}
