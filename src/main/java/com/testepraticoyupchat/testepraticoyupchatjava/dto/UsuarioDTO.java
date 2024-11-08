package com.testepraticoyupchat.testepraticoyupchatjava.dto;

import com.testepraticoyupchat.testepraticoyupchatjava.enums.UsuarioCargo;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(@NotBlank String nome, @NotBlank String sobrenome
        , @NotBlank String email, @NotBlank String senha, @NotBlank UsuarioCargo cargo) {
}
