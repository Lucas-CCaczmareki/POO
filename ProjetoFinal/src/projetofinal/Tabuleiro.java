package projetofinal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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

    
    public boolean verificarDica(int linha, int coluna) {
        for (int j = 0; j < tamanhoN; j++) {
            if (!grade[linha][j].estaVazia()) return true;
        }
        for (int i = 0; i < tamanhoN; i++) {
            if (!grade[i][coluna].estaVazia()) return true;
        }
        return false;
    }

    
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
            
            if (vizinho.estaVazia() && !vizinho.isRevelada()) {
                try {
                    validarRegiao(pokemon, vizinho.getLinha(), vizinho.getColuna());
                    return vizinho;
                } catch (RegiaoInvalidaException e) {
                   
                }
            }
        }
        return null; 
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

    
    public Celula[][] getGrade() { return grade; }
    public int getTamanhoN() { return tamanhoN; }
}