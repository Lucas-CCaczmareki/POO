package projetofinal;

/**
 * Representa uma única célula (quadrado) na grade do tabuleiro.
 * [cite_start]Pode conter um Pokémon ou estar vazia. [cite: 46]
 */
public class Celula {
    private int linha;
    private int coluna;
    private Pokemon pokemon;
    private boolean revelada;

    public Celula(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.pokemon = null; // Começa vazia.
        this.revelada = false;
    }

    // Getters e Setters
    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

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
}