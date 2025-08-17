package projetofinal;

import java.io.Serializable;

public class Celula implements Serializable {

    private final int linha;
    private final int coluna;
    private Pokemon pokemon;
    private boolean revelada;
    private Treinador reveladaPor; 

    public Celula(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.pokemon = null;
        this.revelada = false;
        this.reveladaPor = null;
    }

    /**
     * @param treinador O treinador que está revelando a célula.
     */
    public void revelar(Treinador treinador) {
        this.revelada = true;
        this.reveladaPor = treinador;
    }

    public boolean estaVazia() {
        return pokemon == null;
    }

    // --- Getters e Setters ---
    public Pokemon getPokemon() { return pokemon; }
    public void setPokemon(Pokemon pokemon) { this.pokemon = pokemon; }
    public boolean isRevelada() { return revelada; }
    public void setRevelada(boolean revelada) { this.revelada = revelada; } 
    public int getLinha() { return linha; }
    public int getColuna() { return coluna; }
    public Treinador getReveladaPor() { return reveladaPor; } 
}