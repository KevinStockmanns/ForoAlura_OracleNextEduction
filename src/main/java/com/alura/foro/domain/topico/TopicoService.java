package com.alura.foro.domain.topico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.foro.infra.errores.SolicitudInvalidaException;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;
    

    public RespuestaTopicoDTO crear(RegistroTopicoDTO datos){
        if(datos == null)
            throw new SolicitudInvalidaException("No se envío nada en el cuerpo.");

        if(topicoRepository.existsByTitulo(datos.titulo()))
            throw new SolicitudInvalidaException("Ya existe ese titulo, no debe haber duplicados");

        if(topicoRepository.existsByMensaje(datos.mensaje()))
            throw new SolicitudInvalidaException("Ya existe ese texto, no debe haber duplicados");

        Topico topico = new Topico(datos);
        topicoRepository.save(topico);

        return new RespuestaTopicoDTO(topico);
    }


    public RespuestaTopicoDTO actualizar(Long id, ActualizarTopicoDTO datos) {
        if((datos.titulo() == null && datos.mensaje() == null && datos.autor() == null && datos.curso() == null) || datos == null)
            throw new SolicitudInvalidaException("El cuerpo no debe estar vacio");

        var busqueda = topicoRepository.findById(id);
        if(busqueda.isEmpty())
            throw new SolicitudInvalidaException("No se puede actualizar el id: " + id + " ya que no existe");

        Topico topico = busqueda.get();
        topico.actualizar(datos);
        
        return new RespuestaTopicoDTO(topico);
    }
}
