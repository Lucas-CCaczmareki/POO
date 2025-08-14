package projetofinal;

/**
 * Classe que orquestra todo o jogo, gerenciando o estado, os turnos,
 * os treinadores e o tabuleiro.
 */
public class Jogo {
    private Tabuleiro tabuleiro;
    private Treinador jogador;
    private Computador computador;
    private boolean turnoDoJogador; // Controla de quem é a vez de jogar.

    // Getters e Setters
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public Treinador getJogador() {
        return jogador;
    }

    public void setJogador(Treinador jogador) {
        this.jogador = jogador;
    }

    public Computador getComputador() {
        return computador;
    }

    public void setComputador(Computador computador) {
        this.computador = computador;
    }

    public boolean isTurnoDoJogador() {
        return turnoDoJogador;
    }

    public void setTurnoDoJogador(boolean turnoDoJogador) {
        this.turnoDoJogador = turnoDoJogador;
    }
}
