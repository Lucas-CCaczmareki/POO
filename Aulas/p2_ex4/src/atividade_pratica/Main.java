/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atividade_pratica;

/**
 *
 * @author lucas
 */
public class Main {
    public static void main( String[] args ) {
        //OK
        Aluno a1;
        a1 = new Aluno("Lucas", "24103568", "Computer Science");
        a1.showData();
        System.out.println("");
        
        Aluno a2 = new Aluno("Lili", "000000", "Philosophy");
        Aluno a3 = new Aluno("Sartre", "010101", "Philosophy");
        Aluno a4 = new Aluno("Pitagoras", "c2a2b2", "Mathematics");
            
        //OK
        Turma t1;
        t1 = new Turma("2410.1");
        
        t1.matricularAluno(a1);
        t1.matricularAluno(a2);
        t1.matricularAluno(a3);
        t1.matricularAluno(a4);
        
        t1.showTurma();
    }
}
