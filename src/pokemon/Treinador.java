package pokemon;

import java.util.ArrayList;
import java.util.List;

public class Treinador {
    private String nome;
    private List<Pokemon> time;
    private List<Pokemon> mochila;
    private int pontuacao;
    private Pokemon pokemonPrincipal;
    
    public Treinador(String nome) {
        this.nome = nome;
        this.time = new ArrayList<>();
        this.mochila = new ArrayList<>();
        this.pontuacao = 0;
    }
    
    public void adicionarPokemon(Pokemon pokemon) {
        if (time.isEmpty()) {
            pokemonPrincipal = pokemon;
        }
        time.add(pokemon);
        mochila.add(pokemon);
        pokemon.setSelvagem(false);
        atualizarPontuacao();
    }
    
    public void capturarPokemon(Pokemon pokemon) {
        adicionarPokemon(pokemon);
        System.out.println(nome + " capturou " + pokemon.getNome() + "!");
    }
    
    public void trocarPokemonPrincipal(int indice) {
        if (indice >= 0 && indice < time.size()) {
            pokemonPrincipal = time.get(indice);
            System.out.println(nome + " trocou para " + pokemonPrincipal.getNome());
        }
    }
    
    public void atualizarPontuacao() {
        pontuacao = 0;
        for (Pokemon p : time) {
            pontuacao += p.getExperiencia() + (p.getNivel() * 10);
        }
    }
    
    public Pokemon getPokemonPrincipal() {
        return pokemonPrincipal;
    }
    
    public List<Pokemon> getTime() {
        return time;
    }
    
    public List<Pokemon> getMochila() {
        return mochila;
    }
    
    public int getPontuacao() {
        return pontuacao;
    }
    
    public String getNome() {
        return nome;
    }
    
    public boolean temPokemonVivo() {
        return pokemonPrincipal != null && pokemonPrincipal.getEnergia() > 0;
    }
}
