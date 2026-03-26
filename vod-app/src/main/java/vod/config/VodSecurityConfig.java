package vod.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class VodSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager detailsManager = new JdbcUserDetailsManager();
        detailsManager.setDataSource(dataSource);
        detailsManager.setUsersByUsernameQuery("select username, password, 'true' from user where username=?");
        detailsManager.setAuthoritiesByUsernameQuery("select username, role from role where username=?");
        return detailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.POST, "/webapi/movies").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/webapi/theatres").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/webapi/movies/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/webapi/theatres/**").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults());
        
        return http.build();
    }
}
