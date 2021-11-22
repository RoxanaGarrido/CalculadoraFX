/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
import java.util.Random;
import com.mycompany.m03uf5prac.Calculadora;
import com.mycompany.m03uf5prac.CalculadoraException;
import com.mycompany.m03uf5prac.DivisioPerZeroException;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class ClasseTest {

        @Rule
        public TestName name = new TestName();

        Random rnd = new Random();

        @Test
        public void RF102
        
            (){
        
        // num max de rondes per cada tipus de prova
        int maxRondesPerProva = 100;

            //operands a incloure en l'expresió 
            int numOperands = 6;

            //valor màxim dels operands
            int valorMaxOperands = 999;

            // llista d'operadors admesos en l'expresió
            char[] operadors = new char[]{'+', '-'};

            //incloure parèntesis a l'expresió
            boolean isParentesis = false;

            //incloure part fraccionaria en l'expresió
            boolean isComaFlotant = false;

            //base de numeració
            int base = 10;

            System.out.println("TEST: " + name.getMethodName());

            //gestió correcta d'entrades correctes
            for (int i = 0; i < maxRondesPerProva; i++) {
                // generem expresio numèrica
                String s = generaExpressioNumerica(numOperands, operadors, isParentesis, isComaFlotant, base, valorMaxOperands);

                // mostrem per consola el que s'esta a punt d'avaluar
                System.out.print(s + " ");

                try {

                    // obtenim el resultat
                    Double r1 = Calculadora.avalua(s, base);

                    // obtenim contrast del resultat
                    Expression expression = new ExpressionBuilder(s).build();
                    Double r2 = expression.evaluate();

                    //verifiquem resaultats
                    Assert.assertEquals(r1, r2);

                    // i els resultats OK
                    System.out.println("= " + r1 + " = " + r2);

                } catch (DivisioPerZeroException ex2) {
                    System.out.println("Divisió per zero controlada OK");
                    Assert.assertTrue(true);

                } catch (CalculadoraException ex2) {

                    //aqui no hauria d'entrar mai en aquest test i per tant FAIL
                    Assert.fail();
                } catch (Exception ex3) {

                    //excepció no controlada i per tant, FAIL
                    Assert.fail();
                }
            }

            //gestió correcta d'entrades errònies
            // caracters no vàlids
            try {
                Calculadora.avalua("G23+K99", base);
                Assert.fail();
            } catch (CalculadoraException ex) {
                Assert.assertTrue(true);
            }

            //operadors fora de lloc
            try {
                Calculadora.avalua("99+43+", base);
                Assert.fail();
            } catch (CalculadoraException ex) {
                Assert.assertTrue(true);
            }

            //operadors fora de lloc
            try {
                Calculadora.avalua("99+-+-+-43", base);
                Assert.fail();
            } catch (CalculadoraException ex) {
                Assert.assertTrue(true);
            }
        }

        @Test
        public void RF103
        
            (){
        
        // num max de rondes per cada tipus de prova
        int maxRondesPerProva = 100;

            //operands a incloure en l'expresió 
            int numOperands = 6;

            //valor màxim dels operands
            int valorMaxOperands = 999;

            // llista d'operadors admesos en l'expresió
            char[] operadors = new char[]{'+', '-', '*', '/'};

            //incloure parèntesis a l'expresió
            boolean isParentesis = false;

            //incloure part fraccionaria en l'expresió
            boolean isComaFlotant = false;

            //base de numeració
            int base = 10;

            System.out.println("TEST: " + name.getMethodName());

            //gestió correcta d'entrades correctes
            for (int i = 0; i < maxRondesPerProva; i++) {
                // generem expresio numèrica
                String s = generaExpressioNumerica(numOperands, operadors, isParentesis, isComaFlotant, base, valorMaxOperands);

                // mostrem per consola el que s'esta a punt d'avaluar
                System.out.print(s + " ");

                try {

                    // obtenim el resultat
                    Double r1 = Calculadora.avalua(s, base);

                    // obtenim contrast del resultat
                    Expression expression = new ExpressionBuilder(s).build();
                    Double r2 = expression.evaluate();

                    //verifiquem resaultats
                    Assert.assertEquals(r1, r2);

                    // i els resultats OK
                    System.out.println("= " + r1 + " = " + r2);

                } catch (DivisioPerZeroException ex2) {
                    System.out.println("Divisió per zero controlada OK");
                    Assert.assertTrue(true);

                } catch (CalculadoraException ex2) {

                    //aqui no hauria d'entrar mai en aquest test i per tant FAIL
                    Assert.fail();
                } catch (Exception ex3) {

                    //excepció no controlada i per tant, FAIL
                    Assert.fail();
                }
            }

            //gestió correcta d'entrades errònies
            // caracters no vàlids
            try {
                Calculadora.avalua("G23+K99", base);
                Assert.fail();
            } catch (CalculadoraException ex) {
                Assert.assertTrue(true);
            }

            //operadors fora de lloc
            try {
                Calculadora.avalua("99+43+", base);
                Assert.fail();
            } catch (CalculadoraException ex) {
                Assert.assertTrue(true);
            }

            //operadors fora de lloc
            try {
                Calculadora.avalua("99+-+-+-43", base);
                Assert.fail();
            } catch (CalculadoraException ex) {
                Assert.assertTrue(true);
            }
        }

        @Test
        public void RF105
        
            (){
        
        // num max de rondes per cada tipus de prova
        int maxRondesPerProva = 100;

            //operands a incloure en l'expresió 
            int numOperands = 3;

            //valor màxim dels operands
            int valorMaxOperands = 99;

            // llista d'operadors admesos en l'expresió
            char[] operadors = new char[]{'+', '-', '*', '/', '^'};

            //incloure parèntesis a l'expresió
            boolean isParentesis = true;

            //incloure part fraccionaria en l'expresió
            boolean isComaFlotant = false;

            //base de numeració
            int base = 10;

            System.out.println("TEST: " + name.getMethodName());

            //gestió correcta d'entrades correctes
            for (int i = 0; i < maxRondesPerProva; i++) {
                // generem expresio numèrica
                String s = generaExpressioNumerica(numOperands, operadors, isParentesis, isComaFlotant, base, valorMaxOperands);

                // mostrem per consola el que s'esta a punt d'avaluar
                System.out.print(s + " ");

                try {

                    // obtenim el resultat
                    Double r1 = Calculadora.avalua(s, base);

                    // obtenim contrast del resultat
                    Expression expression = new ExpressionBuilder(s).build();
                    Double r2 = expression.evaluate();

                    //verifiquem resaultats
                    Assert.assertEquals(r1, r2);

                    // i els resultats OK
                    System.out.println("= " + r1 + " = " + r2);

                } catch (DivisioPerZeroException ex2) {
                    System.out.println("Divisió per zero controlada OK");
                    Assert.assertTrue(true);

                } catch (CalculadoraException ex2) {

                    //aqui no hauria d'entrar mai en aquest test i per tant FAIL
                    Assert.fail();
                } catch (Exception ex3) {

                    //excepció no controlada i per tant, FAIL
                    Assert.fail();
                }
            }

            //gestió correcta d'entrades errònies
            // caracters no vàlids
            try {
                Calculadora.avalua("G23+K99", base);
                Assert.fail();
            } catch (CalculadoraException ex) {
                Assert.assertTrue(true);
            }

            //operadors fora de lloc
            try {
                Calculadora.avalua("99+43+", base);
                Assert.fail();
            } catch (CalculadoraException ex) {
                Assert.assertTrue(true);
            }

            //operadors fora de lloc
            try {
                Calculadora.avalua("99+-+-+-43", base);
                Assert.fail();
            } catch (CalculadoraException ex) {
                Assert.assertTrue(true);
            }
        }

        @Test
        public void RF107
        
            (){
        
        // num max de rondes per cada tipus de prova
        int maxRondesPerProva = 100;

            //operands a incloure en l'expresió 
            int numOperands = 3;

            //valor màxim dels operands
            int valorMaxOperands = 99;

            // llista d'operadors admesos en l'expresió
            char[] operadors = new char[]{'+', '-', '*', '/', '^'};

            //incloure parèntesis a l'expresió
            boolean isParentesis = true;

            //incloure part fraccionaria en l'expresió
            boolean isComaFlotant = true;

            //base de numeració
            int base = 10;

            System.out.println("TEST: " + name.getMethodName());

            //gestió correcta d'entrades correctes
            for (int i = 0; i < maxRondesPerProva; i++) {
                // generem expresio numèrica
                String s = generaExpressioNumerica(numOperands, operadors, isParentesis, isComaFlotant, base, valorMaxOperands);

                // mostrem per consola el que s'esta a punt d'avaluar
                System.out.print(s + " ");

                try {

                    // obtenim el resultat
                    Double r1 = Calculadora.avalua(s, base);

                    // obtenim contrast del resultat
                    Expression expression = new ExpressionBuilder(s).build();
                    Double r2 = expression.evaluate();

                    //verifiquem resaultats
                    Assert.assertEquals(r1, r2);

                    // i els resultats OK
                    System.out.println("= " + r1 + " = " + r2);

                } catch (DivisioPerZeroException ex2) {
                    System.out.println("Divisió per zero controlada OK");
                    Assert.assertTrue(true);

                } catch (CalculadoraException ex2) {

                    //aqui no hauria d'entrar mai en aquest test i per tant FAIL
                    Assert.fail();
                } catch (Exception ex3) {

                    //excepció no controlada i per tant, FAIL
                    Assert.fail();
                }
            }

            //gestió correcta d'entrades errònies
            // caracters no vàlids
            try {
                Calculadora.avalua("G23+K99", base);
                Assert.fail();
            } catch (CalculadoraException ex) {
                Assert.assertTrue(true);
            }

            //operadors fora de lloc
            try {
                Calculadora.avalua("99+43+", base);
                Assert.fail();
            } catch (CalculadoraException ex) {
                Assert.assertTrue(true);
            }

            //operadors fora de lloc
            try {
                Calculadora.avalua("99+-+-+-43", base);
                Assert.fail();
            } catch (CalculadoraException ex) {
                Assert.assertTrue(true);
            }

            //separadors decimals fora de lloc
            try {
                Calculadora.avalua("99+43.45.67.54", base);
                Assert.fail();
            } catch (CalculadoraException ex) {
                Assert.assertTrue(true);
            }
        }

        @Test
        public void RF109
        
            (){
        
        // num max de rondes per cada tipus de prova
        int maxRondesPerProva = 100;

            //operands a incloure en l'expresió 
            int numOperands = 3;

            //valor màxim dels operands
            int valorMaxOperands = 99;

            // llista d'operadors admesos en l'expresió
            char[] operadors = new char[]{'+', '-', '*', '/', '^'};

            //incloure parèntesis a l'expresió
            boolean isParentesis = true;

            //incloure part fraccionaria en l'expresió
            boolean isComaFlotant = true;

            //base de numeració
            int base = 2;

            System.out.println("TEST: " + name.getMethodName());

            //gestió correcta d'entrades correctes
            for (int i = 0; i < maxRondesPerProva; i++) {
                // generem expresio numèrica
                String s = generaExpressioNumerica(numOperands, operadors, isParentesis, isComaFlotant, base, valorMaxOperands);

                // mostrem per consola el que s'esta a punt d'avaluar
                System.out.print(s + " ");

                try {

                    // obtenim el resultat
                    Double r1 = Calculadora.avalua(s, base);

                    // i els resultats OK
                    System.out.println("= " + r1);

                } catch (DivisioPerZeroException ex2) {
                    System.out.println("Divisió per zero controlada OK");
                    Assert.assertTrue(true);

                } catch (CalculadoraException ex2) {

                    //aqui no hauria d'entrar mai en aquest test i per tant FAIL
                    Assert.fail();
                } catch (Exception ex3) {

                    //excepció no controlada i per tant, FAIL
                    Assert.fail();
                }
            }

            //gestió correcta d'entrades errònies
            // caracters no vàlids
            try {
                Calculadora.avalua("G23+K99", base);
                Assert.fail();
            } catch (CalculadoraException ex) {
                Assert.assertTrue(true);
            }

            //operadors fora de lloc
            try {
                Calculadora.avalua("AB9+43+", base);
                Assert.fail();
            } catch (CalculadoraException ex) {
                Assert.assertTrue(true);
            }

            //operadors fora de lloc
            try {
                Calculadora.avalua("9A+-+-+-AA", base);
                Assert.fail();
            } catch (CalculadoraException ex) {
                Assert.assertTrue(true);
            }
        }

        @Test
        public void RF112
        
            (){
        
        // num max de rondes per cada tipus de prova
        int maxRondesPerProva = 100;

            //operands a incloure en l'expresió 
            int numOperands = 3;

            //valor màxim dels operands
            int valorMaxOperands = 99;

            // llista d'operadors admesos en l'expresió
            char[] operadors = new char[]{'+', '-', '*', '/', '^'};

            //incloure parèntesis a l'expresió
            boolean isParentesis = true;

            //incloure part fraccionaria en l'expresió
            boolean isComaFlotant = true;

            //base de numeració
            int base = 16;

            System.out.println("TEST: " + name.getMethodName());

            //gestió correcta d'entrades correctes
            for (int i = 0; i < maxRondesPerProva; i++) {
                // generem expresio numèrica
                String s = generaExpressioNumerica(numOperands, operadors, isParentesis, isComaFlotant, base, valorMaxOperands);

                // mostrem per consola el que s'esta a punt d'avaluar
                System.out.print(s + " ");

                try {

                    // obtenim el resultat
                    Double r1 = Calculadora.avalua(s, base);

                    // i els resultats OK
                    System.out.println("= " + r1);

                } catch (DivisioPerZeroException ex2) {
                    System.out.println("Divisió per zero controlada OK");
                    Assert.assertTrue(true);

                } catch (CalculadoraException ex2) {

                    //aqui no hauria d'entrar mai en aquest test i per tant FAIL
                    Assert.fail();
                } catch (Exception ex3) {

                    //excepció no controlada i per tant, FAIL
                    Assert.fail();
                }
            }

            //gestió correcta d'entrades errònies
            // caracters no vàlids
            try {
                Calculadora.avalua("G0101+K12", base);
                Assert.fail();
            } catch (CalculadoraException ex) {
                Assert.assertTrue(true);
            }

            //operadors fora de lloc
            try {
                Calculadora.avalua("0101+1101+", base);
                Assert.fail();
            } catch (CalculadoraException ex) {
                Assert.assertTrue(true);
            }

            //operadors fora de lloc
            try {
                Calculadora.avalua("0010+-+-+-01001", base);
                Assert.fail();
            } catch (CalculadoraException ex) {
                Assert.assertTrue(true);
            }
        }
        /**
         * *
         * Genera un string amb una expressió numèrica
         *
         * @param numOperands El nº d'operands de l'expressió
         * @param operadors La llista d'operadors binaris (que operen sobre dos
         * operands) s'han d'utilitzar
         * @param isParentesis Si es volen fer sevir agrupacions amb parèntesis
         * @param isComaFlotant Si es volen generar operands amb coma flotant o
         * enters
         * @param base La base de numeració dels operands generats
         * @param valorMax El valor màxim alsolut que poden tenir els operands
         * @return
         */
/*
    private String generaExpressioNumerica(int numOperands, char[] operadors, boolean isParentesis, boolean isComaFlotant, int base, int valorMax) {
        String s = "";

        int operands = numOperands;
        int maxParentesisOberts = (isParentesis ? operands : 0);
        int maxParentesisTancats = 0;
        int pt, po;

        int vMax = valorMax;

        for (int i = 0; i < operands; i++) {
            //parentesis oberts
            po = rnd.nextInt(maxParentesisOberts + 1);
            for (int j = 0; j < po; j++) {
                s += "(";
            }

            maxParentesisOberts -= po;
            maxParentesisTancats += po;

            //nombre en la base seleccionada. Per defecte base 10.
            Integer n_int = rnd.nextInt(valorMax);
            Double n_double = rnd.nextDouble();

            switch (base) {
                case 2:
                    s += Integer.toBinaryString(n_int);
                    break;
                case 16:
                    s += Integer.toHexString(n_int);
                    break;
                default:
                    s += isComaFlotant ? (n_double * valorMax) : (String.format("%d", n_int));
                    break;
            }

            // parentesis tancats
            pt = rnd.nextInt(maxParentesisTancats + 1);

            for (int j = 0; j < pt; j++) {
                s += ")";
            }

            maxParentesisTancats -= pt;

            //afegim operador, excepte que sigui el darrer operand
            if (i < operands - 1) {
                s += operadors[rnd.nextInt(operadors.length)];
            }
        }

        //finalment, posem tots els parentesis tancats que en sobren
        for (int j = 0; j < maxParentesisTancats; j++) {
            s += ")";
        }

        return s;
    }
}
*/
