package projetofinal;

import java.io.Serializable;

/**
 * Representa uma única célula (ou quadrado) na grade do tabuleiro.
 * Esta versão foi simplificada e tornada mais robusta.
 */
public class Celula implements Serializable {

    // As coordenadas agora são 'final', pois nunca mudam após a criação.
    private final int linha;
    private final int coluna;

    private Pokemon pokemon;
    private boolean revelada;
    // O atributo redundante 'reveladaPeloJogador' foi removido.

    public Celula(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.pokemon = null;
        this.revelada = false;
    }

    public boolean estaVazia() {
        return pokemon == null;
    }

    // --- Getters e Setters ---

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public boolean isRevelada() {
        return revelada;
    }

    public void setRevelada(boolean revelada) {
        this.revelada = revelada;
    }

    // Não há setters para linha e coluna, pois são 'final'.
    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }
}