package com.alura.foro.domain.respuesta;

import jakarta.validation.constraints.NotBlank;

public record RegistroRespuestaDTO(
    @NotBlank(message = "La respuesta es obligatoria.")
    String respuesta,

    @NotBlank(message = "El autor es obligatorio.")
    String autor
) {
    
}
