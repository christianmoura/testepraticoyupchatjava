package com.testepraticoyupchat.testepraticoyupchatjava.service;

import com.testepraticoyupchat.testepraticoyupchatjava.enums.UsuarioCargo;
import com.testepraticoyupchat.testepraticoyupchatjava.model.TipoNotificacao;
import com.testepraticoyupchat.testepraticoyupchatjava.model.Usuario;
import com.testepraticoyupchat.testepraticoyupchatjava.repository.TipoNotificacaoRepository;
import com.testepraticoyupchat.testepraticoyupchatjava.service.impl.TipoNotificacaoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@SpringBootTest
public class TipoNotificacaoServiceTest {

    @Mock
    private TipoNotificacaoRepository repository;

    @Mock
    private SecurityContextHolder securityContextHolder;

    @InjectMocks
    private TipoNotificacaoServiceImpl service;

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
    public static final Integer TIPO_NOTIFICACAO_ID = 152;
    public static final String TIPO_NOTIFICACAO_NOME_TIPO = "cellNumber";

    public static final TipoNotificacao tipoNotificacaoIn = TipoNotificacao.builder()
            .id(TIPO_NOTIFICACAO_ID)
            .nomeTipo(TIPO_NOTIFICACAO_NOME_TIPO)
            .usuario(usuarioIn)
            .build();

    @Test
    public void findByNomeTipoTest(){
        when(repository.findByNomeTipo(TIPO_NOTIFICACAO_NOME_TIPO)).thenReturn(tipoNotificacaoIn);
        Assertions.assertEquals(tipoNotificacaoIn, service.findByNomeTipo(TIPO_NOTIFICACAO_NOME_TIPO));
    }

    @Test
    public void saveTipoNotificacaoTest(){

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).thenReturn(usuarioIn);
        when(repository.save(tipoNotificacaoIn)).thenReturn(tipoNotificacaoIn);
        Assertions.assertEquals(tipoNotificacaoIn, service.saveTipoNotificacao(tipoNotificacaoIn));
    }

    @Test
    public void findByUsuarioTest(){
        when(repository.findByUsuario(usuarioIn)).thenReturn(List.of(tipoNotificacaoIn));
        Assertions.assertEquals(List.of(tipoNotificacaoIn), service.findByUsuario(usuarioIn));
    }

}
