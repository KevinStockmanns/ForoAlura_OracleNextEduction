package com.alura.foro.domain.topico;


public record ActualizarTopicoDTO(
    String titulo,

    String mensaje,

    String autor,

    String curso
) {
    
}
