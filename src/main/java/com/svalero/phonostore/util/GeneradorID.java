package com.svalero.phonostore.util;

import java.util.UUID;

public class GeneradorID {
    public static String generarCodigoAleatorio(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
