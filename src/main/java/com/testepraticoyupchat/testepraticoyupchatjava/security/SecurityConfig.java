package com.testepraticoyupchat.testepraticoyupchatjava.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    SecurityFilter securityFilter;

    private static final String[] LISTA_URL_LIVRE_ACESSO = {
            "/swagger-ui/index.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/v3/api-docs"
            , "/swagger-resources", "/swagger-ui.html", "/swagger-ui/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return  httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/usuario").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/news/type").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/news/type/{type}").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/news/type/create").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/news/type/update/{type}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/news/type/delete/{type}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/news/type/me").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/news").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/news/{newsId}").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/news/create").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/news/update/{newsId}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/news/{newsId}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/news/me").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/me").authenticated()
                        .requestMatchers(LISTA_URL_LIVRE_ACESSO).permitAll().anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
