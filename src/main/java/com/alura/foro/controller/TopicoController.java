package com.alura.foro.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.foro.domain.respuesta.RespuestaRespuestaDTO;
import com.alura.foro.domain.topico.ActualizarTopicoDTO;
import com.alura.foro.domain.topico.DetallesTopicoConRespuestaDTO;
import com.alura.foro.domain.topico.DetallesTopicoDTO;
import com.alura.foro.domain.topico.ListadoTopicoDTO;
import com.alura.foro.domain.topico.RegistroTopicoDTO;
import com.alura.foro.domain.topico.RespuestaTopicoDTO;
import com.alura.foro.domain.topico.Topico;
import com.alura.foro.domain.topico.TopicoRepository;
import com.alura.foro.domain.topico.TopicoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController{

    @Autowired
    private TopicoService topicoService;
    
    @Autowired
    private TopicoRepository topicoRepository;


    @PostMapping() @Transactional
    public ResponseEntity<RespuestaTopicoDTO> crearTopico(@RequestBody @Valid RegistroTopicoDTO datos, UriComponentsBuilder uriComponentsBuilder) throws RuntimeException{
        RespuestaTopicoDTO res = topicoService.crear(datos);

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(res.id()).toUri();

        return ResponseEntity.created(uri).body(res);
    }


    @GetMapping()
    public ResponseEntity<Page<ListadoTopicoDTO>> listarTopicos(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        size = (size > 5) ? 5 : size;

        Pageable paginador = PageRequest.of(page, size);
        
        return ResponseEntity.ok(topicoRepository.findByEstadoTrue(paginador).map(ListadoTopicoDTO::new));
    }
    @GetMapping("/inactivos")
    public ResponseEntity<Page<ListadoTopicoDTO>> listarTopicosInactivos(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        size = (size > 5) ? 5 : size;

        Pageable paginador = PageRequest.of(page, size);

        return ResponseEntity.ok(topicoRepository.findByEstadoFalse(paginador).map(ListadoTopicoDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> mostrarTopico(@PathVariable Long id, @RequestParam(defaultValue = "false") Boolean withRes, @RequestParam(defaultValue = "false") Boolean allRes){
        Topico topico = topicoRepository.getReferenceById(id);
        if(withRes){
            List<RespuestaRespuestaDTO> respDTO = topico.getRespuestas()
                .stream()
                .filter(resp -> (allRes || resp.getEstado()))
                .map(respuesta -> new RespuestaRespuestaDTO(respuesta))
                .collect(Collectors.toList());
            
            return ResponseEntity.ok(new DetallesTopicoConRespuestaDTO(topico, respDTO));
        }else{
            return ResponseEntity.ok(new DetallesTopicoDTO(topico));
        }
    }

    @PutMapping("/{id}") @Transactional
    public ResponseEntity<RespuestaTopicoDTO> actualizarTopico(@PathVariable Long id, @RequestBody @Valid ActualizarTopicoDTO datos) throws RuntimeException{
        RespuestaTopicoDTO res = topicoService.actualizar(id, datos);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}") @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.eliminar();
        return ResponseEntity.noContent().build();
    }
}
