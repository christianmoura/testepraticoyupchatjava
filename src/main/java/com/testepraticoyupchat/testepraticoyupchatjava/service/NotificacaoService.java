package com.testepraticoyupchat.testepraticoyupchatjava.service;

import com.testepraticoyupchat.testepraticoyupchatjava.model.Notificacao;
import com.testepraticoyupchat.testepraticoyupchatjava.model.Usuario;

import java.util.List;

public interface NotificacaoService  extends GenericService<Notificacao, Long>{
    Notificacao saveNotificacao(Notificacao notificacao);
    List<Notificacao> findByUsuario(Usuario usuario);
}
