package com.testepraticoyupchat.testepraticoyupchatjava.service;

import com.testepraticoyupchat.testepraticoyupchatjava.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UsuarioService  extends GenericService<Usuario, UUID>{
    public UserDetails findByLogin(String login);
}
