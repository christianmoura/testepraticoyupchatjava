package com.testepraticoyupchat.testepraticoyupchatjava.controller;

import com.testepraticoyupchat.testepraticoyupchatjava.dto.AutenticacaoDTO;

import com.testepraticoyupchat.testepraticoyupchatjava.dto.UsuarioDTO;

import com.testepraticoyupchat.testepraticoyupchatjava.model.Usuario;

import com.testepraticoyupchat.testepraticoyupchatjava.service.UsuarioService;
import com.testepraticoyupchat.testepraticoyupchatjava.service.impl.TokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping(value = "/api")
@AllArgsConstructor
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;



    @GetMapping("/usuario")
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody @Valid UsuarioDTO usuarioDto){
        if (service.findByLogin(usuarioDto.email()) != null) return  ResponseEntity.badRequest().build();
        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDto, usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AutenticacaoDTO authenticationDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.usuario(), authenticationDTO.senha());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Usuario)auth.getPrincipal());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/me")
    public ResponseEntity<UserDetails> me () {
        Object user = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        return ResponseEntity.status(HttpStatus.OK).body(service.findByLogin(((Usuario) user).getUsername()));
    }



}
