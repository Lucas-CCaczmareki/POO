/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pratica_lccaczmareki;

/**
 *
 * @author lucas
 */
public class Turma {
    //Atributos
    private String classCode;
    private Aluno[] alunos; //declara que eu tenho um array da classe Aluno chamado alunos
    private int sizeAlunos;
    
    //Método construtor
    public Turma(){
        classCode = "0";
        alunos = new Aluno[50];
        sizeAlunos = 0;
    }
    
    public Turma(String classCode) {
        this.classCode = classCode;
        this.alunos = new Aluno[50]; //número máximo de alunos
        this.sizeAlunos = 0;
    }
    
    //Metodos
    public void matricularAluno(Aluno novoAluno) {
        if(sizeAlunos < alunos.length){
            this.alunos[sizeAlunos] = novoAluno;
            sizeAlunos += 1; //contador de quantos alunos a turma tem
        } else {
            System.out.println("Turma cheia! Nao foi possivel cadastrar aluno");
        }
        
    }
    
    public void showTurma() {
        int i;
        System.out.println("Turma " + this.classCode);
        for( i = 0; i < this.sizeAlunos; i++ ) {
            System.out.print("Aluno " + i + ": ");
            alunos[i].showData();
        }
    }
}
