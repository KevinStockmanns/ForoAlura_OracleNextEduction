package com.alura.foro.domain.respuesta;

public record RespuestaRespuestaDTO(Long id, String autor, String respuesta, Boolean estado) {
    public RespuestaRespuestaDTO(Respuesta respuesta){
        this(respuesta.getId() ,respuesta.getAutor(), respuesta.getRespuesta(), respuesta.getEstado());
    }
}
