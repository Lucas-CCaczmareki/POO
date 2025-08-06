/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package treinoclasses;

/**
 *
 * @author lucas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Code application here
        Funcionario f1 = new Funcionario("Lucas", "Vendas", 15000, "14/06/25", "24103568");
        f1.mostraDados();
        f1.recebeAumento(5000);
        System.out.println(f1.calculaGanhoAnual());
        f1.mostraDados();
    }
    
}
