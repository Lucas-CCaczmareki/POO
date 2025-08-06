/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ex2.pkg4;

/**
 *
 * @author lucas
 */
public class Main {
    public static void main(String[] args) {
        Livro book1 = new Livro("Grande Sertão: Veredas", "João Guimarães Rosa", 1956);
        Livro book2 = new Livro("Meditações", "Marco Aurélio", 170);
        Livro book3 = new Livro("As 48 leis do poder", "Robert Greene", 2000);

        Biblioteca ufpel = new Biblioteca();
        ufpel.adicionarLivro(book1);
        ufpel.adicionarLivro(book2);
        ufpel.adicionarLivro(book3);

        ufpel.listarLivros();
        ufpel.buscarLivro("Meditações");
        book2.emprestar();
        book2.emprestar();
        book1.devolver();
        book2.devolver();
    }
    
}
