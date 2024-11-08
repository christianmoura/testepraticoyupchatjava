package com.testepraticoyupchat.testepraticoyupchatjava.repository;

import com.testepraticoyupchat.testepraticoyupchatjava.model.Notificacao;
import com.testepraticoyupchat.testepraticoyupchatjava.model.TipoNotificacao;
import com.testepraticoyupchat.testepraticoyupchatjava.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoNotificacaoRepository extends JpaRepository<TipoNotificacao, Integer> {
    TipoNotificacao findByNomeTipo(String nomeTipo);
    List<TipoNotificacao> findByUsuario(Usuario usuario);
}
