package com.alura.foro.domain.topico;

import java.time.LocalDateTime;

public record ListadoTopicoDTO(String titulo, String mensaje, LocalDateTime fecha, Boolean estado, String autor, String curso) {

    public ListadoTopicoDTO(Topico topico){
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getEstado(), topico.getAutor(), topico.getCurso());
    }
    
}
