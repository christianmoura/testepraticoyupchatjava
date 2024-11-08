package com.testepraticoyupchat.testepraticoyupchatjava;

import com.testepraticoyupchat.testepraticoyupchatjava.dto.AutenticacaoDTO;
import com.testepraticoyupchat.testepraticoyupchatjava.dto.NotificacaoDTO;
import com.testepraticoyupchat.testepraticoyupchatjava.dto.TipoNotificacaoDTO;
import com.testepraticoyupchat.testepraticoyupchatjava.dto.UsuarioDTO;
import com.testepraticoyupchat.testepraticoyupchatjava.enums.UsuarioCargo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DtoTests {

    public static final String USUARIO_NOME = "Christian";
    public static final String USUARIO_SOBRENOME = "Bernardino de Moura";
    public static final String USUARIO_EMAIL = "christianbmoura@gmail.com";
    public static final String USUARIO_SENHA = "PassW123";

    public static final Integer TIPO_NOTIFICACAO_ID = 102;
    public static final String TIPO_NOTIFICACAO_NOME_TIPO = "phoneNumber";

    public static final Long NOTIFICACAO_ID = 152L;
    public static final String NOTIFICACAO_TITULO = "Teste de Notificação";
    public static final String NOTIFICACAO_CORPO_MENSAGEM = "Corpo da Mensagem";
    public static final UsuarioDTO usuarioIn = new UsuarioDTO(USUARIO_NOME, USUARIO_SOBRENOME
            , USUARIO_EMAIL, USUARIO_SENHA, UsuarioCargo.ADMIN);
    public static final TipoNotificacaoDTO tipoNotificacaoIn = new TipoNotificacaoDTO(TIPO_NOTIFICACAO_ID, TIPO_NOTIFICACAO_NOME_TIPO);
    public static final NotificacaoDTO notificacaoIn = new NotificacaoDTO(NOTIFICACAO_ID, NOTIFICACAO_TITULO
            , NOTIFICACAO_CORPO_MENSAGEM, null, tipoNotificacaoIn, usuarioIn);
    public static final AutenticacaoDTO autenticacaoIn = new AutenticacaoDTO(USUARIO_EMAIL, USUARIO_SENHA);

    @Test
    public void UsuarioDTOTest(){
        Assertions.assertEquals(USUARIO_NOME, usuarioIn.nome());
    }

    @Test
    public void TipoNotificacaoDTOTest(){
        Assertions.assertEquals(TIPO_NOTIFICACAO_NOME_TIPO, tipoNotificacaoIn.nomeTipo());
    }

    @Test
    public void NotificacaoDTOTest(){
        Assertions.assertEquals(NOTIFICACAO_TITULO, notificacaoIn.titulo());
    }

    @Test
    public void AutenticacaoDTOTest(){
        Assertions.assertTrue(autenticacaoIn.usuario().contains("gmail"));
    }

}
