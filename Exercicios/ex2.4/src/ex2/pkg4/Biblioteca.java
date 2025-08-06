package ex2.pkg4;

import  java.util.ArrayList;

public class Biblioteca {
    //Atributos
    //Aqui o conceito de AGREGAÇÃO é trabalhado
    //A biblioteca contém livros, mas esses livros podem continuar existindo independentes da biblioteca
    ArrayList<Livro> acervo;

    //Construtor
    public Biblioteca(){
        acervo = new ArrayList<Livro>();
    }

    //Métodos
    public void adicionarLivro(Livro livro) {
        acervo.add(livro);
    }

    public Livro buscarLivro(String titulo) {
        for(int i = 0; i < acervo.size(); i++) {
            if(titulo == acervo.get(i).getTitulo()) {
                System.out.println("Livro encontrado!");
                return acervo.get(i);
            }
        }
        return null;
    }

    public void listarLivros() {
        System.out.println("--------------- Acervo ----------------");
        for(int i = 0; i < acervo.size(); i++) {
            System.out.println(acervo.get(i).getTitulo());
        }
        System.out.println("---------------------------------------");
    }
}
