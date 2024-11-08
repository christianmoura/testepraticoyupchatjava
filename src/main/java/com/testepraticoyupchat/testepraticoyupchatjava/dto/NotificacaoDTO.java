package com.testepraticoyupchat.testepraticoyupchatjava.dto;

import com.testepraticoyupchat.testepraticoyupchatjava.model.TipoNotificacao;
import jakarta.validation.constraints.NotBlank;

public record NotificacaoDTO(Long id, @NotBlank String titulo, @NotBlank String corpoNotificacao
        , String imagemDestaque, TipoNotificacaoDTO tipoNotificacaoDTO, UsuarioDTO usuarioDTO) {
}
