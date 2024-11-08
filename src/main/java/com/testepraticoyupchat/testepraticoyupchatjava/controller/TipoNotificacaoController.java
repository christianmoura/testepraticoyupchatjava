package com.testepraticoyupchat.testepraticoyupchatjava.controller;

import com.testepraticoyupchat.testepraticoyupchatjava.dto.TipoNotificacaoDTO;
import com.testepraticoyupchat.testepraticoyupchatjava.model.TipoNotificacao;
import com.testepraticoyupchat.testepraticoyupchatjava.model.Usuario;
import com.testepraticoyupchat.testepraticoyupchatjava.service.TipoNotificacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/news/type")
@AllArgsConstructor
public class TipoNotificacaoController {

    @Autowired
    private TipoNotificacaoService tipoNotificacaoService;

    @GetMapping("/")
    public ResponseEntity<List<TipoNotificacao>> getAllTipoNotificacao(){
        return ResponseEntity.status(HttpStatus.OK).body(tipoNotificacaoService.getAll());
    }

    @GetMapping("/{type}")
    public ResponseEntity<TipoNotificacao> getById(@PathVariable Integer type){
        return ResponseEntity.status(HttpStatus.OK).body(tipoNotificacaoService.getById(type));
    }

    @PostMapping("/create")
    public ResponseEntity<TipoNotificacao> createType(@RequestBody @Valid TipoNotificacaoDTO tipoNotificacaoDTO){
        if (tipoNotificacaoService.findByNomeTipo(tipoNotificacaoDTO.nomeTipo())!= null) return  ResponseEntity.badRequest().build();
        var tipoNotificacao = new TipoNotificacao();

        BeanUtils.copyProperties(tipoNotificacaoDTO, tipoNotificacao);

        return ResponseEntity.status(HttpStatus.CREATED).body(tipoNotificacaoService.saveTipoNotificacao(tipoNotificacao));
    }

    @PutMapping("/update/{type}")
    public ResponseEntity<TipoNotificacao> updateType(@RequestBody @Valid TipoNotificacaoDTO tipoNotificacaoDTO, @PathVariable Integer type){
        var tipoNotificacao = tipoNotificacaoService.getById(type);
        if (Objects.isNull(tipoNotificacao)) return  ResponseEntity.badRequest().build();

        BeanUtils.copyProperties(tipoNotificacaoDTO, tipoNotificacao);

        return ResponseEntity.status(HttpStatus.OK).body(tipoNotificacaoService.save(tipoNotificacao));
    }

    @DeleteMapping("/delete/{type}")
    public ResponseEntity deleteTipoNotificacaoById(@PathVariable Integer type){
        tipoNotificacaoService.removeById(type);
        return ResponseEntity.status(HttpStatus.OK).body("TipoNotificacao deletado com sucesso");
    }

    @GetMapping("/me")
    public ResponseEntity<List<TipoNotificacao>> getAllTipoNotificacaoMe(){
        Object user = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(tipoNotificacaoService.findByUsuario((Usuario) user));
    }
}
