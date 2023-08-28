package com.softlond.store.dominio.excepciones;

public class ProductoNoExistenteException extends Exception {
    public ProductoNoExistenteException(Long id) {
        super("El producto con el ID " + id + " no existe en la base de datos. Por favor verifique.");
    }

    public ProductoNoExistenteException(String nombre) {
        super("El producto con el nombre " + nombre + " no existe en la base de datos. Por favor verifique.");
    }
}
