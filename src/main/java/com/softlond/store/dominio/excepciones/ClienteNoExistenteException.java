package com.softlond.store.dominio.excepciones;

public class ClienteNoExistenteException extends Exception{
    public ClienteNoExistenteException() {
        super("El cliente no existe en base de datos, por favor verifique la cédula.");
    }
}
