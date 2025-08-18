package projetofinal;

import java.io.Serializable;

/* Função do Serializable
 *  Serializable permite que os objetos dessa classe podem ser convertidos
 *  em uma sequência de bytes. É isso que permite salvar o estado dos pokémons em um arquivo
 */

/*
 * Define os atributos e funcionalidades que uma célula do tabuleiro pode ter.
 * Por exemplo, ela pode conter um pokémon, ela pode estar revelada, etc.
 */

public class Celula implements Serializable {

    //Atributos
    private final int linha;
    private final int coluna;
    private Pokemon pokemon;
    private boolean revelada;
    private Treinador reveladaPor; 

    //Construtor
    public Celula(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.pokemon = null;
        this.revelada = false;
        this.reveladaPor = null;
    }

    /**
     * Quando a célula é clicada ela fica revelada.
     * @param treinador O treinador que está revelando a célula.
     */
    public void revelar(Treinador treinador) {
        this.revelada = true;
        this.reveladaPor = treinador;
    }

    /*
     * Retorna se a célula está vazia ou não
     */
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