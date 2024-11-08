package com.testepraticoyupchat.testepraticoyupchatjava.security;

import com.testepraticoyupchat.testepraticoyupchatjava.repository.UsuarioRepository;
import com.testepraticoyupchat.testepraticoyupchatjava.service.impl.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (!Objects.isNull(token)){
            var email = tokenService.validarToken(token);
            UserDetails user = usuarioRepository.findByEmail(email);

            var authentication = new UsernamePasswordAuthenticationToken(user, null
                    , user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        return Objects.isNull(authHeader)? null: authHeader.replace("Bearer ", "");
    }
}