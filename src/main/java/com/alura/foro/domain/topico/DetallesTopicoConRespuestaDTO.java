package com.alura.foro.domain.topico;

import java.time.LocalDateTime;
import java.util.List;

import com.alura.foro.domain.respuesta.RespuestaRespuestaDTO;

public record DetallesTopicoConRespuestaDTO(String titulo, String mensaje, LocalDateTime fecha, Boolean estado, String autor, String curso, List<RespuestaRespuestaDTO> respuestas) {

    public DetallesTopicoConRespuestaDTO(Topico topico, List<RespuestaRespuestaDTO> respuestas){
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getEstado(), topico.getAutor(), topico.getCurso(), respuestas);
    }
    
}
