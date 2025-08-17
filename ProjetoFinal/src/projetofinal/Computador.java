package projetofinal;

public class Computador extends Treinador implements Runnable {
    //Atributos
    private Jogo jogo;

    //Construtor
    public Computador(String nome, Jogo jogo) {
        super(nome);
        this.jogo = jogo;
    }

    //O método "main" dessa Thread, que vai ser executada "simultaneamente"
    @Override
    public void run(){
        while (true) {
           jogo.esperarVezDoComputador();
           try {
               Thread.sleep(1500);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }

           jogo.jogadaDoComputador();
        }
    }
    
}
