package com.softlond.store.dominio.excepciones;

public class DescuentoNoExistenteException extends Exception{
    public DescuentoNoExistenteException() {
        super("No se ha registrado ningún descuento.");
    }
}
