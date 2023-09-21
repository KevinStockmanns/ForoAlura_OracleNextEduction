package com.alura.foro.domain.topico;


public record RespuestaTopicoDTO(Long id, String titulo, String mensaje, String autor, String curso) {

    public RespuestaTopicoDTO(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor(), topico.getCurso());
    }
    
}
