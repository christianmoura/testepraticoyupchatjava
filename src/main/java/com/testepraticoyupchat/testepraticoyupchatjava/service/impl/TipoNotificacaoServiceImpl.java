package com.testepraticoyupchat.testepraticoyupchatjava.service.impl;

import com.testepraticoyupchat.testepraticoyupchatjava.model.Notificacao;
import com.testepraticoyupchat.testepraticoyupchatjava.model.TipoNotificacao;
import com.testepraticoyupchat.testepraticoyupchatjava.model.Usuario;
import com.testepraticoyupchat.testepraticoyupchatjava.repository.TipoNotificacaoRepository;
import com.testepraticoyupchat.testepraticoyupchatjava.service.TipoNotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TipoNotificacaoService")
public class TipoNotificacaoServiceImpl extends GenericServiceImpl<TipoNotificacao, Integer> implements TipoNotificacaoService {

    @Autowired
    private TipoNotificacaoRepository repository;

    @Override
    public TipoNotificacao findByNomeTipo(String nomeTipo){
        return repository.findByNomeTipo(nomeTipo);
    }

    @Override
    public TipoNotificacao saveTipoNotificacao(TipoNotificacao tipoNotificacao) {
        Object user = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        tipoNotificacao.setUsuario((Usuario) user);

        return repository.save(tipoNotificacao);
    }

    @Override
    public List<TipoNotificacao> findByUsuario(Usuario usuario){
        return repository.findByUsuario(usuario);
    }
}

