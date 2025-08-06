/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ex4.pkg1;

/**
 *
 * @author lucas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Motor M1 = new Motor();
        M1.setTipo("V8");
        M1.setPotencia(120);

        Carro C1 = new Carro();
        C1.setMarca("Honda");
        C1.setModelo("Civic");
        C1.setMotor(M1);

        C1.getInfo();

    }
    
}
