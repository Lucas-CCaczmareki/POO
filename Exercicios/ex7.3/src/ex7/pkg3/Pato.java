package ex7.pkg3;

import java.util.Scanner;

public class Pato extends Animal implements Nadavel, Voavel{
    //Construtor
    public Pato(String nome){
        super(nome);
    }

    //Métodos
    @Override
    public void mover() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("(Pato) Deseja voar(1) ou nadar(2)? ");
        int option = scanner.nextInt(); //Lê a resposta do usuário
        scanner.close();

        if(option == 1) {
            voar();
        } else if (option == 2) {
            nadar();
        } else {
            System.out.println("Tropecei... Opção inválida");
        }
        
    }

    @Override
    public void nadar(){
        System.out.println("Pato Nadando... De boa na lagoa");
    }

    @Override
    public void voar(){
        System.out.println("Pato Voando... Suave na nave");
    }

}
