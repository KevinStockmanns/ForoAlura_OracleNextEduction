package com.alura.foro.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long>{

    boolean existsByMensaje(String mensaje);

    boolean existsByTitulo(String titulo);

    Page<Topico> findByEstadoTrue(Pageable paginador);

    Page<Topico> findByEstadoFalse(Pageable paginador);
    
}
