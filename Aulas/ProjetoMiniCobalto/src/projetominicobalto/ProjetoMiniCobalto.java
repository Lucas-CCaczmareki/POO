/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetominicobalto;

import usuarios.*;

/**
 *
 * @author felipe
 */
public class ProjetoMiniCobalto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Usuario[] u = new Usuario[3];
        
        u[0] = new Estudante            ("00100200304", "Luke Skywalker", "20250001");
        u[1] = new ProfessorEfetivo     ("11122233399", "Obiwan Kenobi", "1807788");
        u[2] = new ProfessorSubstituto  ("99988877766", "Darth Vader", 24);
        
        for( int i = 0; i < u.length; i++ ) {
            u[i].imprimir();
        }
    }
    
}
