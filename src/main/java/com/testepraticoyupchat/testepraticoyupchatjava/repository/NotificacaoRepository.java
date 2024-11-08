package com.testepraticoyupchat.testepraticoyupchatjava.repository;

import com.testepraticoyupchat.testepraticoyupchatjava.model.Notificacao;
import com.testepraticoyupchat.testepraticoyupchatjava.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
    List<Notificacao> findByUsuario(Usuario usuario);
}
