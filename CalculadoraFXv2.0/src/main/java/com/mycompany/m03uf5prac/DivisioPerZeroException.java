
package com.mycompany.m03uf5prac;

/**
 * metodo para instanciar mensajes de error de división por cero 
 * @author 
 */

public class DivisioPerZeroException extends CalculadoraException {
       public DivisioPerZeroException(String causa, int indice, int errorType) {
        super(causa,indice,errorType);
    }
    
    public DivisioPerZeroException(String causa, int errorType) {
        super(causa,errorType);
    }
}