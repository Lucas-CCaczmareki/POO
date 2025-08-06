/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex3.pkg1;

/**
 *
 * @author lucas
 */
public class Ponto {
    //Atributos
    private int x;
    private int y;

    //Esse exercício demonstra a sobrecarga de métodos
    //A assinatura do método que define qual é chamado. A assinatura consiste no nome e nos parâmetros
    //O construtor padrão deixa de exisitr quando um construtor é declarado!

    //Construtores
    public Ponto(){
        x = 0;
        y = 0;
    }

    public Ponto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void getInto() {
        System.out.println("(" + x + ", " + y + ")");
    }
}
