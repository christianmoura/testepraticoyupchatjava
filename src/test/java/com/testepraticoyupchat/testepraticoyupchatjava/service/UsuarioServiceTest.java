package com.testepraticoyupchat.testepraticoyupchatjava.service;

import com.testepraticoyupchat.testepraticoyupchatjava.enums.UsuarioCargo;
import com.testepraticoyupchat.testepraticoyupchatjava.model.Usuario;
import com.testepraticoyupchat.testepraticoyupchatjava.repository.UsuarioRepository;
import com.testepraticoyupchat.testepraticoyupchatjava.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioServiceImpl service;

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

    @Test
    public void saveTest(){
        when(repository.save(usuarioIn)).thenReturn(usuarioIn);
        Assertions.assertEquals(usuarioIn, service.save(usuarioIn));
    }

    @Test
    public void findByLoginTest(){
        when(repository.findByEmail(USUARIO_EMAIL)).thenReturn(usuarioIn);
        Assertions.assertEquals(usuarioIn, service.findByLogin(USUARIO_EMAIL));
    }
}
