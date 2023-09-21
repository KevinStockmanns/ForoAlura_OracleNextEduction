package com.alura.foro.domain.respuesta;

import java.time.LocalDateTime;

public record RespuestaRespuestaDTO(Long id, String autor, String respuesta, LocalDateTime fecha, Boolean estado) {
    public RespuestaRespuestaDTO(Respuesta respuesta){
        this(respuesta.getId() ,respuesta.getAutor(), respuesta.getRespuesta(), respuesta.getFechaCreacion(), respuesta.getEstado());
    }
}
