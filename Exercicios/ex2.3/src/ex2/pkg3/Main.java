/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ex2.pkg3;

/**
 *
 * @author lucas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Aluno A1 = new Aluno();
        A1.setNome("Lucas C Caczmareki");
        A1.setNota(10);
        System.out.println("Nota: "  + A1.getNota());
        A1.setNota(-1);
    }
    
}
