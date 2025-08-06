/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atividade_pratica;

/**
 *
 * @author lucas
 */
public class Aluno {
    //Atributos
    private String nome;
    private String matricula;
    private String curso;
    
    //Método construtor:
    public Aluno() {
        nome = "Fulano";
        matricula = "0";
        curso = "-";
    }
    
    public Aluno(String nome, String matricula, String curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
    }
    
    //Métodos
    public void showData() {
        System.out.println(nome + " (" + matricula + ") - " + curso);
    }
    
}
