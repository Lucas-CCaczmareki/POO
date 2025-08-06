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
    private float resultado;

    //Métodos
    public float soma(float x, float y) {
        resultado = x + y;

        System.out.println(x + " + " + y + " = " + resultado);

        return resultado;
    }

    public float subtracao(float x, float y) {
        resultado = x - y;

        System.out.println(x + " - " + y + " = " + resultado);

        return resultado;
    }

    public float multiplicacao(float x, float y) {
        resultado = x * y;

        System.out.println(x + " * " + y + " = " + resultado);
        return resultado;
    }

    
    public float divisao(float x, float y) {
        resultado = x / y;

        System.out.println(x + " / " + y + " = " + resultado);
        return resultado;
    }
}
