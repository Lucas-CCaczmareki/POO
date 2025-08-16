package projetofinal;

import java.io.Serializable;

/**
 * Representa uma única célula (ou quadrado) na grade do tabuleiro do jogo.
 * Cada célula possui coordenadas fixas e pode, opcionalmente, conter um Pokémon.
 * Implementa Serializable para permitir que o estado do jogo seja salvo.
 */
public class Celula implements Serializable {

    private final int linha;
    private final int coluna;
    private Pokemon pokemon;
    private boolean revelada;

    /**
     * Construtor de uma Célula.
     * @param linha A coordenada da linha da célula (fixa).
     * @param coluna A coordenada da coluna da célula (fixa).
     */
    public Celula(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.pokemon = null; // Toda célula começa vazia.
        this.revelada = false; // E não revelada.
    }

    public boolean estaVazia() {
        return this.pokemon == null;
    }

    // --- Getters e Setters ---
    public int getLinha() { return linha; }
    public int getColuna() { return coluna; }
    public Pokemon getPokemon() { return pokemon; }
    public void setPokemon(Pokemon pokemon) { this.pokemon = pokemon; }
    public boolean isRevelada() { return revelada; }
    public void setRevelada(boolean revelada) { this.revelada = revelada; }
}