package projetofinal;

import java.util.Random;


public class Computador extends Treinador implements Runnable{

    
    private transient Jogo jogo;

    public Computador(String nome, Jogo jogo) {
        super(nome);
        this.jogo = jogo;
    }

    
    @Override
    public void run() {
        try {
            
            System.out.println(getNome() + " está pensando...");
            Thread.sleep(2000); 
            Random random = new Random();
            Tabuleiro tabuleiro = jogo.getTabuleiro();
            int tamanho = tabuleiro.getTamanhoN();
            int linha, coluna;

            do {
                linha = random.nextInt(tamanho);
                coluna = random.nextInt(tamanho);
            } while (tabuleiro.getGrade()[linha][coluna].isRevelada());

            System.out.println(getNome() + " escolheu jogar na posição [" + linha + "][" + coluna + "].");
            
            
            jogo.processarJogadaComputador(linha, coluna);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("A thread do computador foi interrompida.");
        }
    }
    
    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }
}