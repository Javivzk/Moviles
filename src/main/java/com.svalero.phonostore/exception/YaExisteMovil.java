package com.svalero.phonostore.exception;

public class YaExisteMovil extends Exception {

    public YaExisteMovil(String msg){
        super(msg);
    }

    public YaExisteMovil() {
        super("ERROR: Ya existe ese vehiculo.");
    }
}
