package pokemon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Gerencia a grade N x N do jogo. Esta versão foi corrigida e completada
 * para incluir todas as validações e métodos de ajuda necessários para
 * as regras do jogo.
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

    private void validarRegiao(Pokemon pokemon, int linha, int coluna) throws RegiaoInvalidaException {
        int meio = tamanhoN / 2;
        String tipo = pokemon.getTipo();
        String erroMsg = "A posição [" + linha + "][" + coluna + "] não é válida para um Pokémon do tipo " + tipo + ".";
        
        // CORRIGIDO: Nomes dos tipos com acentuação correta
        if (linha < meio && coluna < meio) {
            if (!tipo.equals("Água")) throw new RegiaoInvalidaException(erroMsg);
        } else if (linha < meio && coluna >= meio) {
            if (!tipo.equals("Floresta")) throw new RegiaoInvalidaException(erroMsg);
        } else if (linha >= meio && coluna < meio) {
            if (!tipo.equals("Terra")) throw new RegiaoInvalidaException(erroMsg);
        } else if (linha >= meio && coluna >= meio) {
            if (!tipo.equals("Elétrico")) throw new RegiaoInvalidaException(erroMsg);
        }
    }

    /**
     * ADICIONADO: Lógica para a Dica (ATIVIDADE 2).
     * Verifica se existe algum Pokémon na linha ou coluna informada.
     */
    public boolean verificarDica(int linha, int coluna) {
        for (int j = 0; j < tamanhoN; j++) {
            if (!grade[linha][j].estaVazia()) return true;
        }
        for (int i = 0; i < tamanhoN; i++) {
            if (!grade[i][coluna].estaVazia()) return true;
        }
        return false;
    }

    /**
     * ADICIONADO: Lógica para a Fuga do Pokémon (ATIVIDADE 2).
     * Encontra uma célula vazia e válida ao redor de uma posição.
     */
    public Celula encontrarCelulaParaFuga(Pokemon pokemon, int linha, int coluna) {
        List<Celula> vizinhos = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int novaLinha = linha + i;
                int novaColuna = coluna + j;

                if (novaLinha >= 0 && novaLinha < tamanhoN && novaColuna >= 0 && novaColuna < tamanhoN) {
                    vizinhos.add(grade[novaLinha][novaColuna]);
                }
            }
        }
        
        Collections.shuffle(vizinhos);
        
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
    public String getTipoRegiao(int linha, int coluna) {
        int meio = tamanhoN / 2;
        if (linha < meio && coluna < meio) return "Água";
        if (linha < meio && coluna >= meio) return "Floresta";
        if (linha >= meio && coluna < meio) return "Terra";
        return "Elétrico";
    }

    public void removerPokemon(int linha, int coluna) {
        grade[linha][coluna].setPokemon(null);
    }
    
    public boolean temPokemonSelvagem() {
        for (int i = 0; i < tamanhoN; i++) {
            for (int j = 0; j < tamanhoN; j++) {
                if (grade[i][j].getPokemon() != null && grade[i][j].getPokemon().isSelvagem()) {
                    return true;
                }
            }
        }
        return false;
    }

    // --- Getters ---
    public Celula[][] getGrade() { return grade; }
    public int getTamanhoN() { return tamanhoN; }
}