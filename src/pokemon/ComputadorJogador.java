package pokemon;

import java.util.Random;

public class ComputadorJogador implements Runnable {
    private Jogo jogo;
    private boolean jogando;
    private Random random;
    
    public ComputadorJogador(Jogo jogo) {
        this.jogo = jogo;
        this.jogando = true;
        this.random = new Random();
    }
    
    @Override
    public void run() {
        while (jogando && jogo.isJogoAtivo()) {
            try {
                // Simula "tempo de pensar"
                Thread.sleep(1000 + random.nextInt(2000));
                
                if (jogo.getTurno() % 2 == 1) { // Vez do computador
                    fazerJogada();
                }
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    
    private void fazerJogada() {
        if (!jogo.isJogoAtivo()) return;
        
        // Escolhe uma posição aleatória
        int tamanho = jogo.getTabuleiro().getTamanho();
        int linha, coluna;
        
        do {
            linha = random.nextInt(tamanho);
            coluna = random.nextInt(tamanho);
        } while (jogo.getTabuleiro().getCelula(linha, coluna).isRevelada());
        
        // Executa a jogada do computador
        jogo.fazerJogadaJogador(linha, coluna); // Reutiliza o método existente
    }
    
    public void parar() {
        this.jogando = false;
    }
    
    public boolean isJogando() {
        return jogando;
    }
}
