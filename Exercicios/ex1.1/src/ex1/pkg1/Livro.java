
package ex1.pkg1;

public class Livro {
    //Atributos
    private String titulo;
    private String autor;
    private int anoPublicacao;


    //Método construtor

    //Métodos
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public void imprimeInfo(){
        System.out.println("----------------------------------");
        System.out.println("Título: " + this.titulo);
        System.out.println("Autor: " + this.autor);
        System.out.println("Publicação: " + this.anoPublicacao);
        System.out.println("----------------------------------");
    }
}
