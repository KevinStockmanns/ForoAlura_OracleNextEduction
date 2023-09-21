package com.alura.foro.infra.errores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorErrores{

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Void> tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<ErrorValidacionDTO>> tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(ErrorValidacionDTO::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(SolicitudInvalidaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> errorHandlerSinBody(SolicitudInvalidaException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    




    private record ErrorValidacionDTO(String campo, String error){
        public ErrorValidacionDTO(FieldError err){
            this(err.getField(), err.getDefaultMessage());
        }
    }
}
