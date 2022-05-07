package com.svalero.phonostore.util;

import com.svalero.phonostore.exception.DniNoValido;

import java.util.Arrays;

import static com.svalero.phonostore.util.Constants.*;

public class ValidaDni {
    public static boolean validarDNI(String dni) throws DniNoValido {
        boolean res = true;
        res = Arrays.binarySearch(INVALIDOS, dni) < 0
                && REGEXP.matcher(dni).matches()
                && dni.charAt(8) == DIGITO_CONTROL.charAt(Integer.parseInt(dni.substring(0, 8)) % 23);
        if(!res){
            throw new DniNoValido("ERROR: El DNI introducido no es valido.");
        }
        return res;
    }
}
