package ex1.pkg1;

public class Main {
    public static void main(String[] args) {
        Livro L1 = new Livro();
        Livro L2 = new Livro();

        L1.setTitulo("Grande Sertão: Veredas");
        L1.setAutor("João Guimarães Rosa");
        L1.setAnoPublicacao(1956);

        L2.setTitulo("Meditações");
        L2.setAutor("Marco Aurélio");
        L2.setAnoPublicacao(170);

        L1.imprimeInfo();
        L2.imprimeInfo();
    }
    
}
