package projetofinal;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um treinador no jogo, com sua equipe de Pokémon,
 * mochila e pontuação.
 */
public class Treinador {
    private String nome;
    private List<Pokemon> timePokemon;
    private List<Pokemon> mochila;// Armazena Pokémons capturados. [cite: 68]
    private int pontuacao;

    public Treinador(String nome) {
        this.nome = nome;
        this.timePokemon = new ArrayList<>();
        this.mochila = new ArrayList<>();
        this.pontuacao = 0;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Pokemon> getTimePokemon() {
        return timePokemon;
    }

    public void setTimePokemon(List<Pokemon> timePokemon) {
        this.timePokemon = timePokemon;
    }

    public List<Pokemon> getMochila() {
        return mochila;
    }

    public void setMochila(List<Pokemon> mochila) {
        this.mochila = mochila;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
}
