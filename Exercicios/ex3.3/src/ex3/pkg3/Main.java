/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ex3.pkg3;

/**
 *
 * @author lucas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ItemPedido i1 = new ItemPedido();
        ItemPedido i2 = new ItemPedido("Arroz", 10);
        ItemPedido i3 = new ItemPedido("Pipoca", 50, 4.99);

        i1.calcularTotal();
        i2.calcularTotal();
        i3.calcularTotal();
    }
    
}
