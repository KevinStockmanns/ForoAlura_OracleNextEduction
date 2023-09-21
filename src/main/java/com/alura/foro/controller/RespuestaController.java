package com.alura.foro.controller;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.foro.domain.respuesta.RegistroRespuestaDTO;
import com.alura.foro.domain.respuesta.RespuestaRespuestaDTO;
import com.alura.foro.domain.respuesta.RespuestaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;
    
    @PostMapping("/{topico_id}")
    public ResponseEntity<RespuestaRespuestaDTO> registrarRespuesta(@PathVariable(name = "topico_id") Long id, @RequestBody @Valid RegistroRespuestaDTO datos, UriComponentsBuilder uriComponentsBuilder) throws RuntimeException{
        RespuestaRespuestaDTO respuestaDTO = respuestaService.crear(datos, id);
        
        URI uri = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuestaDTO.id()).toUri();

        return ResponseEntity.created(uri).body(respuestaDTO);
    }
}
