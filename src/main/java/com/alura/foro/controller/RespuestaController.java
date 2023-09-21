package com.alura.foro.controller;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.foro.domain.respuesta.ActualizarRespuestaDTO;
import com.alura.foro.domain.respuesta.RegistroRespuestaDTO;
import com.alura.foro.domain.respuesta.Respuesta;
import com.alura.foro.domain.respuesta.RespuestaRepository;
import com.alura.foro.domain.respuesta.RespuestaRespuestaDTO;
import com.alura.foro.domain.respuesta.RespuestaService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @Autowired
    private RespuestaRepository respuestaRepository;
    
    @PostMapping("/{topico_id}")
    public ResponseEntity<RespuestaRespuestaDTO> registrarRespuesta(@PathVariable(name = "topico_id") Long id, @RequestBody @Valid RegistroRespuestaDTO datos, UriComponentsBuilder uriComponentsBuilder) throws RuntimeException{
        RespuestaRespuestaDTO respuestaDTO = respuestaService.crear(datos, id);
        
        URI uri = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuestaDTO.id()).toUri();

        return ResponseEntity.created(uri).body(respuestaDTO);
    }

    @PutMapping("/{id}") @Transactional
    public ResponseEntity<RespuestaRespuestaDTO> actualizarRespuesta(@PathVariable Long id, @RequestBody @Valid ActualizarRespuestaDTO datos) throws RuntimeException{
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuesta.actualizar(datos);
        return ResponseEntity.ok(new RespuestaRespuestaDTO(respuesta));
    }

    @DeleteMapping("/{id}") @Transactional
    public ResponseEntity<Void> eliminarRespuesta(@PathVariable Long id){
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuesta.desactivar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerRespuesta(@PathVariable Long id){
        return ResponseEntity.ok(new RespuestaRespuestaDTO(respuestaRepository.getReferenceById(id)));
    }
}
