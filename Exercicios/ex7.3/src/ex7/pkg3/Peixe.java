package ex7.pkg3;

public class Peixe extends Animal implements Nadavel {
    //Construtor
    public Peixe(String nome){
        super(nome);
    }   

    @Override
    public void mover() {
        nadar();
    }

    @Override
    public void nadar() {
        System.out.println("Peixe Nadando... de boa na água!");
    }
}
