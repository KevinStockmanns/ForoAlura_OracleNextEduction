package com.alura.foro.domain.respuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.foro.domain.topico.Topico;
import com.alura.foro.domain.topico.TopicoRepository;
import com.alura.foro.infra.errores.SolicitudInvalidaException;

@Service
public class RespuestaService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;
    
    public RespuestaRespuestaDTO crear(RegistroRespuestaDTO datos, Long idTopico){

        if(respuestaRepository.existsByRespuesta(datos.respuesta()))
            throw new SolicitudInvalidaException("Ya existe una respuesta así");

        Topico topico = topicoRepository.getReferenceById(idTopico);
        Respuesta respuesta = new Respuesta(datos, topico);

        respuestaRepository.save(respuesta);
        return new RespuestaRespuestaDTO(respuesta);
    }
}
