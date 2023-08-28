package com.softlond.store.dominio.excepciones;

public class CategoriaNoExistenteException extends Exception {
    public CategoriaNoExistenteException(Long id) {
        super("La categoría con el ID " + id + " no existe en base de datos, porfavor verifíquela o creela.");
    }
    public CategoriaNoExistenteException(String nombre) {
        super("La categoría con el nombre " + nombre + " no existe en base de datos, porfavor verifíquela o creela.");
    }
}
