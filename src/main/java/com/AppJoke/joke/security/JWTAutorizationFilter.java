package com.AppJoke.joke.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAutorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = request.getHeader("Authorization");

        if (jwt == null || !jwt.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecParams.SECRET)).build();
        // enlever le préfixe Bearer du jwt
        jwt = jwt.substring(7); // 7 caractères dans "Bearer "
        // décodage du jwt
        DecodedJWT decodedJWT = verifier.verify(jwt);
        // extraction du nom
        String username = decodedJWT.getSubject();
        // récupération des roles
        List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
        // Ajout des roles à l'autority
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String r : roles)
            authorities.add(new SimpleGrantedAuthority(r));
        // cre&tion du user spring
        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, null, authorities);
        // mise à jour du context
        SecurityContextHolder.getContext().setAuthentication(user);
        filterChain.doFilter(request, response);
    }

}
