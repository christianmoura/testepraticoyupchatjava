package com.testepraticoyupchat.testepraticoyupchatjava.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.testepraticoyupchat.testepraticoyupchatjava.enums.UsuarioCargo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Usuario implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String senha;

    @NotBlank
    private UsuarioCargo cargo;

    @JsonManagedReference(value = "tiposNotificacoesReference")
    @OneToMany(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.PERSIST}, mappedBy = "usuario")
    private Set<TipoNotificacao> tiposNotificacoes;

    @JsonManagedReference(value = "notificacoesReference")
    @OneToMany(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.PERSIST}, mappedBy = "usuario")
    private Set<Notificacao> notificacoes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (this.cargo == UsuarioCargo.ADMIN)? List.of(new SimpleGrantedAuthority("ROLE_ADMIN")
                , new SimpleGrantedAuthority("ROLE_USER")): List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
