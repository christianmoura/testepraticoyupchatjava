package com.testepraticoyupchat.testepraticoyupchatjava.controller;

import com.testepraticoyupchat.testepraticoyupchatjava.dto.NotificacaoDTO;
import com.testepraticoyupchat.testepraticoyupchatjava.model.Notificacao;
import com.testepraticoyupchat.testepraticoyupchatjava.model.TipoNotificacao;
import com.testepraticoyupchat.testepraticoyupchatjava.model.Usuario;
import com.testepraticoyupchat.testepraticoyupchatjava.service.NotificacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/news")
@AllArgsConstructor
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;



    @GetMapping("/")
    public ResponseEntity<List<Notificacao>> getAllNotificacao(){
        return ResponseEntity.status(HttpStatus.OK).body(notificacaoService.getAll());
    }

    @GetMapping("/{newsId}")
    public ResponseEntity<Notificacao> getById(@PathVariable Long newsId){
        return ResponseEntity.status(HttpStatus.OK).body(notificacaoService.getById(newsId));
    }

    @PostMapping("/create")
    public ResponseEntity<Notificacao> createNotificacao(@RequestBody @Valid NotificacaoDTO notificacaoDTO){

        var notificacao = new Notificacao();
        var tipoNotificacao = new TipoNotificacao();
        BeanUtils.copyProperties(notificacaoDTO.tipoNotificacaoDTO(), tipoNotificacao);
        BeanUtils.copyProperties(notificacaoDTO, notificacao);
        notificacao.setTipoNotificacao(tipoNotificacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(notificacaoService.saveNotificacao(notificacao));
    }

    @PutMapping("/update/{newsId}")
    public ResponseEntity<Notificacao> updateNotificacao(@RequestBody @Valid NotificacaoDTO notificacaoDTO, @PathVariable Long newsId){

        var notificacao = notificacaoService.getById(newsId);
        var tipoNotificacao = new TipoNotificacao();
        BeanUtils.copyProperties(notificacaoDTO.tipoNotificacaoDTO(), tipoNotificacao);
        BeanUtils.copyProperties(notificacaoDTO, notificacao);
        notificacao.setTipoNotificacao(tipoNotificacao);
        return ResponseEntity.status(HttpStatus.OK).body(notificacaoService.save(notificacao));
    }

    @DeleteMapping("/delete/{newsId}")
    public ResponseEntity deleteNotificacaoById(@PathVariable Long newsId){
        notificacaoService.removeById(newsId);
        return ResponseEntity.status(HttpStatus.OK).body("Notificacao deletado com sucesso");
    }

    @GetMapping("/me")
    public ResponseEntity<List<Notificacao>> getAllNotificacaoMe(){
        Object user = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(notificacaoService.findByUsuario((Usuario) user));
    }

}
