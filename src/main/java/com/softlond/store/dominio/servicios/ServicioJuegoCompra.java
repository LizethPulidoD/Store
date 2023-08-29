package com.softlond.store.dominio.servicios;

import com.softlond.store.dominio.excepciones.DescuentoNoExistenteException;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class ServicioJuegoCompra {
    private final ServicioDescuento servicioDescuento;

    public ServicioJuegoCompra(ServicioDescuento servicioDescuento) {
        this.servicioDescuento = servicioDescuento;
    }

    public Optional<Float> jugar() {
        Random random = new Random();
        int numero = 0;
        int intentos = 0;
        do {
            intentos++;
            numero = random.nextInt(3) + 1;
            if (numero == 1) {
                try {
                    return Optional.of(servicioDescuento.obtenerDescuento().getPorcentajeDescuento());
                } catch (DescuentoNoExistenteException e) {
                    return Optional.of(0f);
                }
            }
        } while (numero != 3 && intentos < 3);

        return Optional.empty();
    }
}
