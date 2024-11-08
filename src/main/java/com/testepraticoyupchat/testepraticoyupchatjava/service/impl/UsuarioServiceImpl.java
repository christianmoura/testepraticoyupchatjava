package com.testepraticoyupchat.testepraticoyupchatjava.service.impl;

import com.testepraticoyupchat.testepraticoyupchatjava.model.Usuario;
import com.testepraticoyupchat.testepraticoyupchatjava.repository.UsuarioRepository;
import com.testepraticoyupchat.testepraticoyupchatjava.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service("UsuarioService")
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, UUID> implements UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Override
    public Usuario save(Usuario usuario) {
        BCryptPasswordEncoder criptografar = new BCryptPasswordEncoder();
        usuario.setSenha(criptografar.encode(usuario.getPassword()));
        return repository.save(usuario);
    }

    @Override
    public UserDetails findByLogin(String login) {
        return repository.findByEmail(login);
    }
}
