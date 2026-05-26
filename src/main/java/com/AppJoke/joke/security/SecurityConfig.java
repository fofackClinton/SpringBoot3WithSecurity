package com.AppJoke.joke.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	AuthenticationManager authMgr;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// pour dire à sprinf quu'on ne gère pas les sessions
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				.csrf(csrf -> csrf.disable())

				.authorizeHttpRequests(requests -> requests
						.requestMatchers("/login").permitAll()
						.requestMatchers("/all").hasAuthority("ADMIN")
						.anyRequest().authenticated())
				// ajout du filtre pour créé le jwt
				.addFilterBefore(new JWTAuthenticationFilter(authMgr),
						UsernamePasswordAuthenticationFilter.class)
				// filtre pour décodé le token lire le user et les roles
				.addFilterBefore(new JWTAutorizationFilter(),
						UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}
