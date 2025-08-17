package pokemon;

import java.io.Serializable;
import java.util.Random;

/**
 * Representa o treinador controlado pelo computador.
 * Herda de Treinador e implementa Runnable para que sua jogada seja
 * aleatória e executada em uma thread separada para cada turno.
 */
public class Computador extends Treinador implements Runnable, Serializable {

    // A referência ao Jogo é 'transient' para não ser salva no arquivo.
    // Ela será reinjetada quando o jogo for carregado.
    private transient Jogo jogo;

    public Computador(String nome, Jogo jogo) {
        super(nome);
        this.jogo = jogo;
    }

    /**
     * INFORMAÇÕES IMPORTANTES: Deve fazer uso de Threads para Jogada do Computador.
     * Este método é o ponto de entrada da thread. Ele executa UMA VEZ por jogada.
     */
    @Override
    public void run() {
        try {
            // Adiciona um delay para simular o "tempo de pensar", como solicitado.
            System.out.println(getNome() + " está pensando...");
            Thread.sleep(2000); // Pausa a execução por 2 segundos

            // Lógica para escolher uma jogada aleatória em uma célula não revelada
            Random random = new Random();
            Tabuleiro tabuleiro = jogo.getTabuleiro();
            int tamanho = tabuleiro.getTamanhoN();
            int linha, coluna;

            do {
                linha = random.nextInt(tamanho);
                coluna = random.nextInt(tamanho);
            } while (tabuleiro.getGrade()[linha][coluna].isRevelada());

            System.out.println(getNome() + " escolheu jogar na posição [" + linha + "][" + coluna + "].");
            
            // Chama o método específico no Jogo para processar a jogada do computador.
            // Isso garante que a lógica de turnos não entre em loop.
            jogo.processarJogadaComputador(linha, coluna);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("A thread do computador foi interrompida.");
        }
    }
    
    /**
     * Método usado para reinjetar a referência ao jogo ao carregar uma partida salva.
     */
    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }
}