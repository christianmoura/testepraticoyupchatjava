package com.testepraticoyupchat.testepraticoyupchatjava.service;

import com.testepraticoyupchat.testepraticoyupchatjava.enums.UsuarioCargo;
import com.testepraticoyupchat.testepraticoyupchatjava.model.Notificacao;
import com.testepraticoyupchat.testepraticoyupchatjava.model.TipoNotificacao;
import com.testepraticoyupchat.testepraticoyupchatjava.model.Usuario;
import com.testepraticoyupchat.testepraticoyupchatjava.repository.NotificacaoRepository;
import com.testepraticoyupchat.testepraticoyupchatjava.repository.TipoNotificacaoRepository;
import com.testepraticoyupchat.testepraticoyupchatjava.service.impl.NotificacaoServiceImpl;
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
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@SpringBootTest
public class NotificationServiceTest {

    @Mock
    private NotificacaoRepository repository;

    @Mock
    private TipoNotificacaoRepository tipoNotificacaoRepository;

    @InjectMocks
    private NotificacaoServiceImpl service;

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
    public static final Long NOTIFICACAO_ID = 102L;
    public static final String NOTIFICACAO_TITULO = "Teste de Notificação";
    public static final String NOTIFICACAO_CORPO_NOTIFICACAO = "Corpo da mensagem";

    public static final Notificacao notificacaoIn = Notificacao.builder()
            .id(NOTIFICACAO_ID)
            .titulo(NOTIFICACAO_TITULO)
            .corpoNotificacao(NOTIFICACAO_CORPO_NOTIFICACAO)
            .tipoNotificacao(tipoNotificacaoIn)
            .usuario(usuarioIn)
            .build();

    @Test
    public void saveNotificacaoTest(){
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).thenReturn(usuarioIn);
        when(tipoNotificacaoRepository.findById(TIPO_NOTIFICACAO_ID)).thenReturn(Optional.of(tipoNotificacaoIn));
        when(repository.save(notificacaoIn)).thenReturn(notificacaoIn);
        Assertions.assertEquals(notificacaoIn, service.saveNotificacao(notificacaoIn));
    }

    @Test
    public void findByUsuarioTest(){
        when(repository.findByUsuario(usuarioIn)).thenReturn(List.of(notificacaoIn));
        Assertions.assertEquals(List.of(notificacaoIn), service.findByUsuario(usuarioIn));
    }

}
