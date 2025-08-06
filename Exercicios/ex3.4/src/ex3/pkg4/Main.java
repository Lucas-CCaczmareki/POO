/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ex3.pkg4;

/**
 *
 * @author lucas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Funcionario F1 = new Funcionario(0, "Ricardão");
        Funcionario F2 = new Funcionario(1, "Hermógenes", 1000, "Comedor de pipoca");


        //Demonstrando a sobrecarga de métodos e o encapsulamento (getters e setters)
        F1.setSalario(100);
        F1.aumentarSalario(0.1);


        F2.aumentarSalario(150, true);

        System.out.println("Salário " + F1.getNome() + " = " + F1.getSalario());
        System.out.println("Salário " + F2.getNome() + " = " + F2.getSalario());
    }
    
}
