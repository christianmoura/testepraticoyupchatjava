package com.testepraticoyupchat.testepraticoyupchatjava.dto;

import jakarta.validation.constraints.NotBlank;

public record TipoNotificacaoDTO(Integer id,  @NotBlank String nomeTipo) {
}
