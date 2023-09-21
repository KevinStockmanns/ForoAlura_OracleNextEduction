package com.alura.foro.domain.respuesta;

import java.time.LocalDateTime;


import com.alura.foro.domain.topico.Topico;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "Respuesta")
@Table(name = "respuestas")
@Getter @NoArgsConstructor @AllArgsConstructor @ToString
public class Respuesta {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    private String respuesta;
    
    private String autor;

    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;


    public Respuesta(@Valid RegistroRespuestaDTO datos, Topico topico) {
        this.respuesta = datos.respuesta();
        this.autor = datos.autor();

        this.fechaCreacion = LocalDateTime.now();
        this.estado = true;
        this.topico = topico;
    }


    public void actualizar(@Valid ActualizarRespuestaDTO datos) {
        if(datos.respuesta() != null)
            this.respuesta = datos.respuesta();
    }


    public void desactivar() {
        this.estado = false;
    }
}
