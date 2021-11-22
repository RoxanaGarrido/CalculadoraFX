/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.m03uf5prac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Classe principal donde se realitza la lógica del programa 
 */
public class Calculadora {
    private static final HashMap<Character,Double> variables = new HashMap<>();
    private static final HashMap<Character,Integer> opPrecedence = new HashMap<>();
    private static final Stack<Double> pVal = new Stack<>();
    private static final Stack<Character> pOper = new Stack<>();
    private static final Stack<Character> pPars = new Stack<>();
    
    // Inicializa el HashMap de precedencia de operadores y el de variables
    // Además, limpia las tres pilas por si ha saltado alguna excepción
    private static void initialize() {
        pVal.clear();
        pOper.clear();
        pPars.clear();
        opPrecedence.put('+', 0);
        opPrecedence.put('-', 0);
        opPrecedence.put('/', 1);
        opPrecedence.put('*', 1);
        opPrecedence.put('^', 2);
        opPrecedence.put('(', -1);
        variables.put('π', Math.PI);
    }
    
    public static double avalua(String exp, int base) throws CalculadoraException, DivisioPerZeroException {
        initialize();
        int index = 0;
        int expLength = exp.length();
        double number1;
        double number2;
        char oper;
        char currentChar;
        
        if (!isValidBase(base)) {
            throw new CalculadoraException("La base introducida no es correcta.", index, 1);
        }
        
        
        while (index < expLength) {
            currentChar = exp.charAt(index);
            
            if (isOperation(currentChar)) {
                if (!checkOperation(exp,index,base)) {
                    throw new CalculadoraException("El signo introducido no es válido.",index,1);
                }
                while (!pOper.isEmpty() && /*pOper.peek() != '(' &&*/ precedence(pOper.peek(),currentChar)) {
                    number1 = pVal.pop();
                    number2 = pVal.pop();
                    oper = pOper.pop();
                    
                    try {
                        pVal.push(operate(oper,number1,number2));
                    } catch (DivisioPerZeroException dpz){
                        throw new DivisioPerZeroException(dpz.getCausa(),index,dpz.getErrorType());
                    }
                }
                    
                pOper.push(currentChar);
                index++;
            } else if (isVariable(currentChar)) {
                pVal.push(variables.get(currentChar));
                index++;
            } else if (isLeftParenthesis(currentChar)) {
                pOper.push(currentChar);
                pPars.push(currentChar);
                index++;
            }else if (isRightParenthesis(currentChar)) {
                //Comprobación de paréntesis correctos
                if (pPars.isEmpty()) {
                    // Lanzar excepción: el paréntesis es incorrecto
                    throw new CalculadoraException("Paréntesis derecho sin ninguno izquierdo.",index,1);
                } else {
                    // Sacamos el paréntesis que estaba en la pila de paréntesis
                    pPars.pop(); 
                }
                //Cálculos operacionales
                while (!pOper.isEmpty() && !isLeftParenthesis(pOper.peek())) {
                    number1 = pVal.pop();
                    number2 = pVal.pop();
                    oper = pOper.pop();
                    
                    try {
                        pVal.push(operate(oper,number1,number2));
                    } catch (DivisioPerZeroException dpz){
                        throw new DivisioPerZeroException(dpz.getCausa(),index,dpz.getErrorType());
                    }
                }
                
                // Ya no necesitamos el paréntesis izquierdo, así que hacemos pop()
                // y lo descartamos
                pOper.pop();
                index++;
            } else if (isValidCharacter(currentChar,base)) {
                index = index + getNumberFromString(exp,index,base); 
            } else {
                String error = "ERROR: El carácter " + currentChar + " no es un número, operación o variable";
                throw new CalculadoraException(error,index,1);
            }
        }
        
        while (!pOper.isEmpty()) {
            number1 = pVal.pop();
            number2 = pVal.pop();
            oper = pOper.pop();
            try {
                pVal.push(operate(oper,number1,number2));
            } catch (DivisioPerZeroException dpz){
                throw new DivisioPerZeroException(dpz.getCausa(),index,dpz.getErrorType());
            }
        }
        
        // Después de mirar todo el array, miramos que no queden paréntesis izquierdos en la pila
        if (!pPars.isEmpty()) {
            // Lanzar excepción: hay paréntesis sin match
            throw new CalculadoraException("Quedan paréntesis sin cerrar",index,1);
        }
        return pVal.pop();
    }
    
    // Devuelve TRUE si la operación de la pila tiene mayor precedencia que la operación que estamos mirando
    private static boolean precedence (Character previousOp, Character currentOp) {
        return opPrecedence.get(previousOp) >= opPrecedence.get(currentOp);
    }
    
    // Devuelve el resultado de operar dos números
    private static double operate (char operation, double number1, double number2) throws DivisioPerZeroException {
        double result = 0;
        
        switch (operation) {
            case '+': result = number1 + number2;
                      break;
            case '-': result = number2 - number1;
                      break; 
            case '*': result = number1*number2;
                      break;
            case '/': result = (double) number2/number1;
                        if (number1 == 0) {
                            throw new DivisioPerZeroException("No se puede dividir por 0.",1);
                        }
                      break;
            case '^': result = Math.pow(number2, number1);
                      break;
        }
        return result;
    }
    
    // Devuelve TRUE si el símbolo coincide con '+' o '-'
    private static boolean isOperation (char symbol) {
        return (symbol == '+') || (symbol == '-') || (symbol == '*') || (symbol == '/')
                || (symbol == '^');
    }
    
    //Devuelve TRUE si el símbolo coincide con '('
    private static boolean isLeftParenthesis (char symbol) {
        return symbol == '(';
    }
    
    //Devuelve TRUE si el símbolo coincide con ')'
    private static boolean isRightParenthesis (char symbol) {
        return symbol == ')';
    }
    
    //Devuelve TRUE si el símbolo está recogido en el HashMap
    private static boolean isVariable (char symbol) {
        return variables.containsKey(symbol);
    }
    
    // Devuelve el número de dígitos de un número
    private static int numberOfDigits (double number) {
        return (int) (Math.log10(number) + 1);
    }

    // Devuelve el número de carácteres del valor parseado de una string y hace push
    // en la pila de valores después de haber convertido el valor a decimal
    private static int getNumberFromString (String exp, int pos, int base) {
        String regex = "([0-9A-Fa-f]+[.]{0,1}[0-9A-Fa-f]*)";
        String numero;
        int longitudNumero;
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(exp.substring(pos));
        
        if (matcher.find()) {
            numero = matcher.group();
            longitudNumero = numero.length();
            
            switch (base) {
                case 2: pVal.push(binaryToDecimal(numero));
                        break;
                case 10: pVal.push(Double.parseDouble(numero));
                         break;
                case 16: pVal.push(hexToDecimal(numero));
                         break;
            }
            return longitudNumero;               
        } else {
            throw new IllegalArgumentException("No numbers were found.");
        }
    }
    
    // Devuelve TRUE si la operación es válida
    private static boolean checkOperation(String exp, int pos, int base) throws CalculadoraException {
        char oper = exp.charAt(pos);
        boolean res = false;
        
        if (pos == 0) {
            if (oper == '-') {
                res = true;
            }
        } else if (pos != exp.length()-1) {
            char beforeChar = exp.charAt(pos-1);
            char afterChar = exp.charAt(pos+1);
            
            if (isValidCharacter(beforeChar,base) && isValidCharacter(afterChar,base)) {
                return true;
            }
            if (beforeChar == ')' && afterChar == '(') {
                return true;
            } 
            if (beforeChar == ')' && isValidCharacter(afterChar,base)) {
                return true;
            }
            if (isValidCharacter(beforeChar,base) && afterChar == '(') {
                return true;
            }
            if (beforeChar == '(' && isValidCharacter(afterChar,base)) {
                return true;
            }
        }
        
        return res;
    }
    
    // Convierte número binario a decimal
    private static double binaryToDecimal (String binary) {
        return (double) Integer.parseInt(binary,2);
    }
    
    // Convierte número hexadecimal a decimal
    private static double hexToDecimal (String hex) {
        return (double) Integer.parseInt(hex,16);
    }
    
    // Devuelve TRUE si el carácter es decimal: 0-9
    private static boolean isDecimal (char number) {
        boolean res = false;
        
        if (number == '0' || number == '1' || number == '2' || number == '3' ||
                number == '4' || number == '5' || number == '6' || number == '7' ||
                number == '8' || number == '9') {
            res = true;
        }
        
        return res;
    }
    
    // Devuelve TRUE si el carácter es binario: 0-1
    private static boolean isBinary (char number) {
        boolean res = false;
        
        if (number == '0' || number == '1') {
            res = true;
        }
        
        return res;
    }
    
    // Devuelve TRUE si el carácter es hexadecimal: 0-9/a-f/A-F
    private static boolean isHex (char number) {
        boolean res = false;
        
        if (number == '0' || number == '1' || number == '2' || number == '3' ||
                number == '4' || number == '5' || number == '6' || number == '7' ||
                number == '8' || number == '9' || number == 'a' || number == 'b'
                || number == 'c' || number == 'd' || number == 'e' || number == 'f' 
                || number == 'A' || number == 'B'
                || number == 'C' || number == 'D' || number == 'E' || number == 'F') {
            res = true;
        }
        
        return res;
    }
    
    // Devuelve TRUE si el carácter es válido, dependiendo de la base
    private static boolean isValidCharacter (char number, int base) {
        boolean res = false;

        if (base == 2 && isBinary(number)) {
            res = true;
        } else if (base == 10 && isDecimal(number)) {
            res = true;
        } else if (base == 16 && isHex(number)) {
            res = true;
        }
        
        return res;
    }
    
    // Devuelve TRUE si la base es 2,10 o 16
    private static boolean isValidBase (int base) {
        boolean res = false;
        if (base == 2 || base == 10 || base == 16) {
            res = true;
        }
            
        return res;
    }
}

