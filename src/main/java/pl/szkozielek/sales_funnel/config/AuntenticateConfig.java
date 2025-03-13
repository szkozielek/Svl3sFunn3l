package pl.szkozielek.sales_funnel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuntenticateConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {

        return http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/login").permitAll()
            .requestMatchers("/register").permitAll()
            .requestMatchers("/logout").permitAll()
            .requestMatchers("/css/**").permitAll()
            .requestMatchers("/img/**").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(form -> { 
            form.loginPage("/login").permitAll();
        })
        .logout(config -> config.logoutSuccessUrl("/login"))
        .build();   
    }

    @Bean
    public PasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }
}
