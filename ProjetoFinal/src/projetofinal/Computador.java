package projetofinal;

import java.io.Serializable;
import java.util.Random;

/**
 * Representa o treinador controlado pelo computador.
 * Herda de Treinador e implementa Runnable para que sua jogada seja
 * aleatória e executada em uma thread separada, simulando inteligência reativa.
 */
public class Computador extends Treinador implements Runnable, Serializable {

    // A referência ao Jogo é transient para evitar problemas de serialização em cascata.
    // Ela será reinjetada ao carregar um jogo.
    private transient Jogo jogo;

    public Computador(String nome, Jogo jogo) {
        super(nome);
        this.jogo = jogo;
    }

    /**
     * INFORMAÇÕES IMPORTANTES: Deve fazer uso de Threads para Jogada do Computador.
     * Este método é o ponto de entrada da thread do computador.
     */
    @Override
    public void run() {
        try {
            // Adiciona um delay para simular o "tempo de pensar".
            System.out.println(getNome() + " está pensando...");
            Thread.sleep(2000); // Pausa a execução por 2 segundos

            // Lógica para escolher uma jogada aleatória
            Random random = new Random();
            Tabuleiro tabuleiro = jogo.getTabuleiro();
            int tamanho = tabuleiro.getTamanhoN();
            int linha, coluna;

            // Procura uma célula que ainda não foi revelada
            do {
                linha = random.nextInt(tamanho);
                coluna = random.nextInt(tamanho);
            } while (tabuleiro.getGrade()[linha][coluna].isRevelada());

            System.out.println(getNome() + " escolheu jogar na posição [" + linha + "][" + coluna + "].");

            // Chama o jogo de volta para processar a jogada escolhida na thread principal da GUI
            jogo.processarJogadaComputador(linha, coluna);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("A thread do computador foi interrompida.");
        }
    }

    // Método para reinjetar a referência ao jogo ao carregar
    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }
}