package projetofinal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Gerencia a grade N x N do jogo, dividida em células.
 * É responsável por validar e posicionar os Pokémon em suas 4 regiões corretas.
 * Também fornece métodos de ajuda para funcionalidades do jogo como "Dica" e "Fuga".
 */
public class Tabuleiro implements Serializable {

    private final Celula[][] grade;
    private final int tamanhoN;

    public Tabuleiro(int tamanhoN) {
        if (tamanhoN % 2 != 0 || tamanhoN <= 0) {
            throw new IllegalArgumentException("O tamanho N do tabuleiro deve ser um número par e positivo.");
        }
        this.tamanhoN = tamanhoN;
        this.grade = new Celula[tamanhoN][tamanhoN];
        for (int i = 0; i < tamanhoN; i++) {
            for (int j = 0; j < tamanhoN; j++) {
                grade[i][j] = new Celula(i, j);
            }
        }
    }

    /**
     * Posiciona um Pokémon em uma dada coordenada, após validar a região.
     * @param pokemon O Pokémon a ser posicionado.
     * @param linha A linha alvo.
     * @param coluna A coluna alvo.
     * @throws RegiaoInvalidaException se a região for incorreta para o tipo do Pokémon.
     */
    public void posicionarPokemon(Pokemon pokemon, int linha, int coluna) throws RegiaoInvalidaException {
        if (linha < 0 || linha >= tamanhoN || coluna < 0 || coluna >= tamanhoN) {
            throw new IndexOutOfBoundsException("Posição [" + linha + "][" + coluna + "] está fora do tabuleiro.");
        }
        if (!this.grade[linha][coluna].estaVazia()) {
            throw new IllegalStateException("A célula [" + linha + "][" + coluna + "] já está ocupada.");
        }

        validarRegiao(pokemon, linha, coluna);
        this.grade[linha][coluna].setPokemon(pokemon);
    }

    /**
     * Valida se as coordenadas correspondem à região do tipo do Pokémon.
     * As definições seguem o documento de trabalho[cite: 61, 62, 63, 64].
     * @throws RegiaoInvalidaException se a validação falhar.
     */
    private void validarRegiao(Pokemon pokemon, int linha, int coluna) throws RegiaoInvalidaException {
        int meio = tamanhoN / 2;
        String tipo = pokemon.getTipo();
        String erroMsg = "A posição [" + linha + "][" + coluna + "] não é válida para um Pokémon do tipo " + tipo + ".";

        if (linha < meio && coluna < meio) { // Região 1 (Água) [cite: 61]
            if (!tipo.equals("Água")) throw new RegiaoInvalidaException(erroMsg);
        } else if (linha < meio && coluna >= meio) { // Região 2 (Floresta) [cite: 62]
            if (!tipo.equals("Floresta")) throw new RegiaoInvalidaException(erroMsg);
        } else if (linha >= meio && coluna < meio) { // Região 3 (Terra) [cite: 63]
            if (!tipo.equals("Terra")) throw new RegiaoInvalidaException(erroMsg);
        } else if (linha >= meio && coluna >= meio) { // Região 4 (Eletricidade) [cite: 64]
            if (!tipo.equals("Elétrico")) throw new RegiaoInvalidaException(erroMsg);
        }
    }

    /**
     * ATIVIDADE 2: Lógica para a Dica[cite: 114, 115].
     * Verifica se existe algum Pokémon (de qualquer tipo) na linha ou coluna informada.
     * @return true se um Pokémon foi encontrado, false caso contrário.
     */
    public boolean verificarDica(int linha, int coluna) {
        // Verifica a linha
        for (int j = 0; j < tamanhoN; j++) {
            if (!grade[linha][j].estaVazia()) return true;
        }
        // Verifica a coluna
        for (int i = 0; i < tamanhoN; i++) {
            if (!grade[i][coluna].estaVazia()) return true;
        }
        return false;
    }

    /**
     * ATIVIDADE 2: Lógica para a Fuga do Pokémon[cite: 124].
     * Encontra uma célula vazia e válida ao redor de uma posição.
     * @param linha A linha original do Pokémon.
     * @param coluna A coluna original do Pokémon.
     * @return Uma Célula vazia e válida para a fuga, ou null se não houver espaço.
     */
    public Celula encontrarCelulaParaFuga(Pokemon pokemon, int linha, int coluna) {
        List<Celula> vizinhos = new ArrayList<>();
        // Adiciona todos os 8 vizinhos válidos (dentro do tabuleiro)
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // Pula a própria célula
                int novaLinha = linha + i;
                int novaColuna = coluna + j;

                if (novaLinha >= 0 && novaLinha < tamanhoN && novaColuna >= 0 && novaColuna < tamanhoN) {
                    vizinhos.add(grade[novaLinha][novaColuna]);
                }
            }
        }

        // Embaralha para tornar a fuga aleatória
        Collections.shuffle(vizinhos);

        // Retorna a primeira célula vazia que respeite a regra de região do Pokémon
        for (Celula vizinho : vizinhos) {
            if (vizinho.estaVazia()) {
                try {
                    validarRegiao(pokemon, vizinho.getLinha(), vizinho.getColuna());
                    return vizinho; // Encontrou uma célula válida e vazia
                } catch (RegiaoInvalidaException e) {
                    // Continua procurando se a região não for válida
                }
            }
        }
        return null; // Não encontrou nenhuma célula válida para fuga
    }

    // --- Getters ---
    public Celula[][] getGrade() { return grade; }
    public int getTamanhoN() { return tamanhoN; }
}
