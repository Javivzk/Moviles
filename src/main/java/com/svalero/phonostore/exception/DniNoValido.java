package com.svalero.phonostore.exception;

public class DniNoValido extends Exception{

    public DniNoValido(String msg){
        super(msg);
    }

    public DniNoValido(){
        super("ERROR: El DNI introducido no es valido");
    }
}
