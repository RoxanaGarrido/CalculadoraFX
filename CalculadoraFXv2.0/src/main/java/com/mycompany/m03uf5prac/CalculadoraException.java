/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.m03uf5prac;

/**
 * metodos para instanciar mensajes de error para mostrar en la captura
 * @author 
 */
public class CalculadoraException extends Exception {
     private String causa;
    private int indice;
    private int errorType;

    public CalculadoraException(String causa, int indice, int errorType) {
        this.causa = causa;
        this.indice = indice;
        this.errorType = errorType;
    }
    
    public CalculadoraException(String causa, int errorType) {
        this.causa = causa;
        this.errorType = errorType;
    }
    
    public String getCausa() {
        return causa;
    }

    public int getIndice() {
        return indice;
    }
    
    public int getErrorType() {
        return errorType;
    }

}
