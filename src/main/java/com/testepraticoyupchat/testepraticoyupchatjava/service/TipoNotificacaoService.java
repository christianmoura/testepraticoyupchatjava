package com.testepraticoyupchat.testepraticoyupchatjava.service;

import com.testepraticoyupchat.testepraticoyupchatjava.model.TipoNotificacao;
import com.testepraticoyupchat.testepraticoyupchatjava.model.Usuario;

import java.util.List;

public interface TipoNotificacaoService extends GenericService<TipoNotificacao, Integer>{
    public TipoNotificacao findByNomeTipo(String nomeTipo);

    public TipoNotificacao saveTipoNotificacao(TipoNotificacao tipoNotificacao);

    public List<TipoNotificacao> findByUsuario(Usuario usuario);
}
