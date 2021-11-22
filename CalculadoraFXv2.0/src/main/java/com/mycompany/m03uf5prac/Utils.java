/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.m03uf5prac;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.MatchResult;
import java.util.stream.Collectors;

/**
 *
 * @author garri
 */
public class Utils {

    public static Pattern patternOp = Pattern.compile("([+-/\\*\\(\\)\\^])+");

    /**
     * Convierte una expresión Hexadecimal a Decimal, y devuelve la
     * string completa (valores y operadores).
     *
     * @param expresio
     * @return NuevaExpresio
     */
    public static String StringHexToDec(String expresio) {

        List<String> listMatches = new ArrayList<String>();
        List<String> listOpMatches = new ArrayList<String>();
        List<String> decimales = new ArrayList<String>();
        String NuevaExpresio;

        //pattern para grupos de letras y/o números
        Pattern patternHex = Pattern.compile("([\\dA-F]?)+");

        Matcher matcherHex = patternHex.matcher(expresio);
        Matcher matcherOp = patternOp.matcher(expresio);

        while (matcherHex.find()) {
            //add match to the list
            listMatches.add(matcherHex.group());
        }

        while (matcherOp.find()) {
            listOpMatches.add(matcherOp.group());
        }
        
        listMatches.removeIf(s -> s.equals("")); //El list de matches guarda cadenas vacías

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < listMatches.size(); i++) {
            String decimal = Integer.toString(HexToDec(listMatches.get(i)));
            sb.append(decimal);
            if (i != listMatches.size() - 1 || listMatches.get(i) != ")") {
                sb.append('.');
            }
        }
        NuevaExpresio = sb.toString();

        for (int i = 0; i < listOpMatches.size(); i++) {
            NuevaExpresio = NuevaExpresio.replaceFirst("\\.", listOpMatches.get(i));
        }

        return NuevaExpresio;
    }

    /**
     * 
     * @param valor
     * @return 
     */
    public static int HexToDec(String valor) {
        int decimal = 0;
        //Posición para potencia
        int pos = 0;
        // Recorrer la cadena de derecha a izquierda
        for (int i = valor.length() - 1; i >= 0; i--) {
            int caracter = caracterHex(valor.charAt(i));
            int elevado = (int) (Math.pow(16, pos) * caracter);
            decimal += elevado;
            // Avanzar en la potencia
            pos++;
        }
        return decimal;
    }

    /**
     * 
     * @param valor
     * @return 
     */
    public static int BinToDec(String valor) {
        int decimal = 0;
        //Posición para potencia
        int pos = 0;
        // Recorrer la cadena de derecha a izquierda
        for (int i = valor.length() - 1; i >= 0; i--) {
            int caracter = 1;
            if (valor.charAt(i) == '0') {
                caracter = 0;
            }
            int elevado = (int) (Math.pow(2, pos) * caracter);
            decimal += elevado;
            // Avanzar en la potencia
            pos++;
        }
        return decimal;
    }

    /**
     * 
     * @param c
     * @return 
     */
    public static int caracterHex(char c) {
        switch (c) {
            case 'A':
                return 10;
            case 'B':
                return 11;
            case 'C':
                return 12;
            case 'D':
                return 13;
            case 'E':
                return 14;
            case 'F':
                return 15;
            default:
                return Integer.parseInt(String.valueOf(c));
        }
    }

    /**
     * Convierte una expresión Binaria a Decimal, y devuelve la
     * string completa (valores y operadores).
     * @param expresio
     * @return 
     */
    public static String StringBinToDec(String expresio) {

        List<String> listMatches = new ArrayList<String>();
        List<String> listOpMatches = new ArrayList<String>();
        List<String> decimales = new ArrayList<String>();
        String NuevaExpresio;

        Pattern patternBin = Pattern.compile("([0-1]?)+");

        Matcher matcherBin = patternBin.matcher(expresio);
        Matcher matcherOp = patternOp.matcher(expresio);

        while (matcherBin.find()) {
            listMatches.add(matcherBin.group());
        }

        while (matcherOp.find()) {
            listOpMatches.add(matcherOp.group());
        }
        listMatches.removeIf(s -> s.equals("")); //El list de matches guarda cadenas vacías

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < listMatches.size(); i++) {
            String decimal = Integer.toString(BinToDec(listMatches.get(i)));
            sb.append(decimal);
            if (i != listMatches.size() - 1 || listMatches.get(i) != ")") {
                sb.append('.');
            }
        }
        NuevaExpresio = sb.toString();

        for (int i = 0; i < listOpMatches.size(); i++) {
            NuevaExpresio = NuevaExpresio.replaceFirst("\\.", listOpMatches.get(i));
        }

        return NuevaExpresio;
    }

    /*
    public static void main(String[] args) {
        System.out.println(StringHexToDec("A+(FF)*2/(2+25D)"));
        System.out.println(StringBinToDec("01+(10)*0/(1+110)"));
    }
     */
}
