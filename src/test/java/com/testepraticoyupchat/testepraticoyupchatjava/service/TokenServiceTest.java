package com.testepraticoyupchat.testepraticoyupchatjava.service;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.testepraticoyupchat.testepraticoyupchatjava.enums.UsuarioCargo;
import com.testepraticoyupchat.testepraticoyupchatjava.model.Usuario;
import com.testepraticoyupchat.testepraticoyupchatjava.service.impl.TokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;


import java.util.UUID;

import static org.mockito.Mockito.doThrow;

@SpringBootTest
public class TokenServiceTest {

    public static final String INVALID_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ0ZXN0ZXByYXRpY295dXBjaGF0Iiwic3ViIjoibWFydGEudGF2YXJlc0Bib2wuY29tLmJyIiwiZXhwIjoxNzMxMDE5MDgzfQ.VSC5oPDeh2W48G2M705MWggil1-JBI3ChVXEIbhfc0U";
    public static final UUID USUARIO_ID = UUID.fromString("12762e0f-efab-40b0-9582-5225d2949e17");
    public static final String USUARIO_EMAIL = "marta.tavares@bol.com.br";
    public static final String USUARIO_NOME = "marta";
    public static final String USUARIO_SENHA = "$2a$10$j8IDpwe7yUR4x/zfSJXXruVPs0d3WS/y7y4BpSUlIQDHMVqExzo7G";
    public static final Usuario usuarioIn = Usuario.builder()
            .id(USUARIO_ID)
            .email(USUARIO_EMAIL)
            .nome(USUARIO_NOME)
            .senha(USUARIO_SENHA)
            .cargo(UsuarioCargo.USER)
            .build();

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry){
        registry.add("api.security.secret", () -> "testepratico");
        registry.add("api.security.expiration.time.minutes", () -> 5);
        registry.add("api.security.zone.time", () -> "-03:00");
    }

    @Autowired
    private TokenService service;

    @Mock
    private TokenService invalidService;

    @Test
    public void GenerateTokenValidTest(){
        Assertions.assertNotNull(service.generateToken(usuarioIn));
    }

    @Test
    public void ValidateTokenValidTest(){
        Assertions.assertNotNull(service.validarToken(service.generateToken(usuarioIn)));
    }

    @Test
    public void GenerateTokenInvalidTest(){
        doThrow(JWTCreationException.class).when(invalidService).generateToken(usuarioIn);
        Assertions.assertThrows(JWTCreationException.class, () -> invalidService.generateToken(usuarioIn));
    }

    @Test
    public void ValidateTokenInvalidTest(){
        doThrow(JWTVerificationException.class).when(invalidService).validarToken(INVALID_TOKEN);
        Assertions.assertThrows(JWTVerificationException.class, () -> invalidService.validarToken(INVALID_TOKEN));
    }
}
