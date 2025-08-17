package projetofinal;

import java.util.*;

public class Treinador {
    //Atributos
    private String nome;
    private List<Pokemon> mochila;
    private int pontuacao;

    //Construtor
    public Treinador(String nome) {
        this.nome = nome;
        mochila = new ArrayList<>();
        pontuacao = 0;
    }

    //Métodos
    public void addPkm(Pokemon pkm) {
        mochila.add(pkm);
    }

    //Getters e setters
    public List<Pokemon> getMochila() {
        return mochila;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public String getNome() {
        return nome;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
}
