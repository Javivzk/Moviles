package com.svalero.phonostore.exception;

public class ClienteNoEncontrado extends  Exception{
    public ClienteNoEncontrado(){
        super("ERROR: Cliente no encontrado");
    }
}
