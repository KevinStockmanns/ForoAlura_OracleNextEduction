package com.alura.foro.infra.errores;

public class SolicitudInvalidaException extends RuntimeException{
    public SolicitudInvalidaException(String s){
        super(s);
    }
}
