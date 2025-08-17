package projetofinal;

import java.util.Random;

public class Jogo {
    private Tabuleiro tabuleiro;
    private Treinador jogador;
    private Computador computador;
    private boolean turnoDoJogador;

    public Jogo() {
        this.tabuleiro = new Tabuleiro();
        this.jogador = new Treinador("Ash");
        this.turnoDoJogador = true;

        //Aqui cria o computador e inicia a thread dele
        this.computador = new Computador("Gary", this); //passa a referência desse jogo
        Thread threadDoComputador = new Thread(this.computador);
        threadDoComputador.start(); //chama o run dentro da thread do computador em paralelo
    }

    //Esse método é chamado pela classe que gerencia a interface gráfica, quando um jogador clica num botão
    public synchronized void jogadaDoJogador(int linha, int coluna) {
        //Aqui a linha e a coluna devem vir do clique do botão
        
        if(!turnoDoJogador) return; //Não faz nada se tiver na vez da CPU

        System.out.println("Jogador clicou em [" + linha + "][" + coluna + "]");

        //Aqui entraria toda a lógica de batalha/captura que vai ser ignorada por enquanto

        //Depois do jogador, passa a vez pro computador
        this.turnoDoJogador = false;
        notifyAll(); //Fala pra thread do computador trabaia;
    }

    // Método que o computador vai chamar
    public void jogadaDoComputador() {
        System.out.println("Computador está escolhendo uma jogada...");
        Random random = new Random();

        int linha = random.nextInt(tabuleiro.getTamanhoN());
        int coluna = random.nextInt(tabuleiro.getTamanhoN());


        System.out.println("-> Computador clicou em [" + linha + "][" + coluna + "]");

        //Aqui entraria a lógica de batalha/captura do computador que eu vou ignorar por enquanto

        // Troca pra vez do jogador
        this.turnoDoJogador = true;
    }

    //Método de sincronização para a Thread do computador esperar (?)
    public synchronized void esperarVezDoComputador() {
        while(turnoDoJogador) {
            try {
                wait(); //a thread da cpu espera
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

    //Getter pra interface gráfica acessar o tabuleiro
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
    
}
