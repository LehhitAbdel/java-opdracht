package be.ehb.eindproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/register", "/login", "/inventory", "/basket", "/addToBasket", "/basket/reserve", "/user/**").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection for form submissions
                .formLogin().disable() // Disable default login form
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)); // Ensure a session is always created

        return http.build();
    }
}
