/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex1.pkg2;

/**
 *
 * @author lucas
 */
public class Calculadora {
    //Atributos
    //private float resultado;

    //Demonstrando a sobrecarga de métodos com algo que não é um construtor!
    //Métodos
    public int soma(int x, int y) {
        //resultado = x + y;

        System.out.println(x + " + " + y + " = " + (x+y));
        return x + y;
        //return resultado;
    }

   
    public double soma(double x, double y) {
        //resultado = x + y;

        System.out.println(x + " + " + y + " = " + (x+y));
        return x + y;
        //return resultado;
    }

    public int soma(int x, int y, int z) {
        System.out.println(x + " + " + y + " + " + z + " = " + (x+y+z));
        return x + y + z;
    }

    public float subtracao(float x, float y) {
        //resultado = x - y;

        System.out.println(x + " - " + y + " = " + (x+y));
        return x + y;

        //return resultado;
    }

    public float multiplicacao(float x, float y) {
        //resultado = x * y;

        System.out.println(x + " * " + y + " = " + (x+y));
        return x + y;
        //return resultado;
    }

    
    public float divisao(float x, float y) {
        //resultado = x / y;

        System.out.println(x + " / " + y + " = " + (x+y));
        return x + y;
        //return resultado;
    }
}
