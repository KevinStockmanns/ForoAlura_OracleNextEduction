package com.alura.foro.domain.topico;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter @AllArgsConstructor @NoArgsConstructor
public class Topico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "mensaje")
    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "estado")
    private Boolean estado;
    
    @Column(name = "autor")
    private String autor;
    
    @Column(name = "curso")
    private String curso;


    public Topico(@Valid RegistroTopicoDTO datos) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.autor = datos.autor();
        this.curso = datos.curso();

        this.fechaCreacion = LocalDateTime.now();
        this.estado = true;
    }


    public void actualizar(ActualizarTopicoDTO datos) {
        if(datos.titulo() != null)
            this.titulo = datos.titulo();
        
        if(datos.mensaje() != null)
            this.mensaje = datos.mensaje();
        
        if(datos.autor() != null)
            this.autor = datos.autor();

        if(datos.curso() != null)
            this.curso = datos.curso();
    }


    public void eliminar() {
        this.estado = false;
    }
}
