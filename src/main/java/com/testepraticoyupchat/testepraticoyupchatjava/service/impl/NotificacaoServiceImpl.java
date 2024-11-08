package com.testepraticoyupchat.testepraticoyupchatjava.service.impl;

import com.testepraticoyupchat.testepraticoyupchatjava.model.Notificacao;
import com.testepraticoyupchat.testepraticoyupchatjava.model.TipoNotificacao;
import com.testepraticoyupchat.testepraticoyupchatjava.model.Usuario;
import com.testepraticoyupchat.testepraticoyupchatjava.repository.NotificacaoRepository;
import com.testepraticoyupchat.testepraticoyupchatjava.repository.TipoNotificacaoRepository;
import com.testepraticoyupchat.testepraticoyupchatjava.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("NotificacaoService")
public class NotificacaoServiceImpl extends GenericServiceImpl<Notificacao, Long> implements NotificacaoService {

    @Autowired
    private NotificacaoRepository repository;

    @Autowired
    private TipoNotificacaoRepository tipoNotificacaoRepository;

    @Override
    public Notificacao saveNotificacao(Notificacao notificacao){
        Object user = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        TipoNotificacao tipoNotificacao = notificacao.getTipoNotificacao();
        notificacao.setTipoNotificacao(tipoNotificacaoRepository.findById(tipoNotificacao.getId()).orElse(null));
        notificacao.setUsuario((Usuario) user);
        return repository.save(notificacao);
    }

    @Override
    public List<Notificacao> findByUsuario(Usuario usuario) {
        return repository.findByUsuario(usuario);
    }
}
