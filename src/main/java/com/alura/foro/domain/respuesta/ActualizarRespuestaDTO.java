package com.alura.foro.domain.respuesta;

import jakarta.validation.constraints.NotBlank;

public record ActualizarRespuestaDTO(
    @NotBlank(message = "La respuesta es obligatoria.")
    String respuesta
) {
    
}
