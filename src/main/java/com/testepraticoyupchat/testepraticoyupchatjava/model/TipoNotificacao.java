package com.testepraticoyupchat.testepraticoyupchatjava.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class TipoNotificacao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "SERIAL")
    private Integer id;
    @NotBlank
    private String nomeTipo;

    @JsonBackReference("tiposNotificacoesReference")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @JsonManagedReference(value = "notificacoesReference")
    @OneToMany(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.PERSIST}, mappedBy = "tipoNotificacao")
    private Set<Notificacao> notificacoes;
}
