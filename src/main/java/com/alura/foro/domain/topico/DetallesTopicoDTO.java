package com.alura.foro.domain.topico;

import java.time.LocalDateTime;

public record DetallesTopicoDTO(Long id, String titulo, String mensaje, LocalDateTime fecha, Boolean estado, String autor, String curso) {

    public DetallesTopicoDTO(Topico topico){
        this(topico.getId() ,topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getEstado(), topico.getAutor(), topico.getCurso());
    }
    
}
