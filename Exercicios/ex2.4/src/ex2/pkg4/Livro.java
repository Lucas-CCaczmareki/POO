/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2.pkg4;

/**
 *
 * @author lucas
 */
public class Livro {
    //Atributos
    private String titulo;
    private String autor;
    private int ano;
    private boolean disponivel;

    //Construtor
    public Livro(String titulo, String autor, int ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.disponivel = true;
    }

    //Métodos
    public boolean emprestar() {
        if(this.disponivel == true) {
            this.disponivel = false;
            System.out.println( this.titulo + " emprestado com sucesso!");
            return true;
        } else {
            System.out.println("O livro já foi emprestado!");
            return false;
        }
    }

    public boolean devolver() {
        if(this.disponivel == true) {
            System.out.println("Esse livro não foi emprestado!");
            return false;
        } else {
            System.out.println(this.titulo + " devolvido com sucesso!");
            return true;
        }
    }

    //Getters
    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
    }
    public int getAno() {
        return ano;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }    
}
