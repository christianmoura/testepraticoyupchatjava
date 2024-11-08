package com.testepraticoyupchat.testepraticoyupchatjava.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Notificacao  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "SERIAL")
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String corpoNotificacao;

    private String imagemDestaque;

    @JsonBackReference("notificacoesReference")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @JsonBackReference("notificacoesReference")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_TIPO_NOTIFICACAO", nullable = false)
    private TipoNotificacao tipoNotificacao;
}
