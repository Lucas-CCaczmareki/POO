package ex7.pkg3;

public class Passaro extends Animal implements Voavel {
    //Construtor
    public Passaro(String nome) {
        super(nome);
    }

    //Métodos
    public void mover() {
        voar();
    }

    public void voar() {
        System.out.println("Passaro Voando e migrando seguindo a canção...");
    }

}
