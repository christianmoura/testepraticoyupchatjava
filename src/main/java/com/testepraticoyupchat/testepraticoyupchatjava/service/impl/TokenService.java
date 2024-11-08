package com.testepraticoyupchat.testepraticoyupchatjava.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.testepraticoyupchat.testepraticoyupchatjava.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String secret;

    @Value("${api.security.expiration.time.minutes}")
    private Integer expirationTime;

    @Value("${api.security.zone.time}")
    private String zoneTime;

    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String  token = JWT.create().withIssuer("testepraticoyupchat").withSubject((usuario.getUsername()))
                    .withExpiresAt(LocalDateTime.now().plusMinutes(expirationTime)
                            .toInstant(ZoneOffset.of(zoneTime))).sign(algorithm);

            return  token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar o token", e);
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer("testepraticoyupchat").build().verify(token).getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }
}
