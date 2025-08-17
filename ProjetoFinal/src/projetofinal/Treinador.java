package projetofinal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Treinador implements Serializable {
    private String nome;
    private List<Pokemon> mochila; 
    private int pontuacao;
    private Pokemon pokemonPrincipal; 

    public Treinador(String nome) {
        this.nome = nome;
        this.mochila = new ArrayList<>();
        this.pontuacao = 0;
        this.pokemonPrincipal = null;
    }

    /**
     * Define o Pokémon inicial do treinador.
     * Este é o único Pokémon que começa "fora" da mochila.
     * @param pokemon O Pokémon a ser definido como principal.
     */
    public void adicionarPokemonInicial(Pokemon pokemon) {
        if (this.pokemonPrincipal == null) {
            pokemon.setSelvagem(false);
            this.pokemonPrincipal = pokemon;
        }
    }

    /**
     * @param pokemon O Pokémon capturado.
     */
    public void capturarPokemon(Pokemon pokemon) {
        pokemon.setSelvagem(false);
        this.mochila.add(pokemon);
        System.out.println(nome + " capturou " + pokemon.getNome() + "!");
        atualizarPontuacao();
    }

    /**
     * @param indiceNaMochila O índice do Pokémon na mochila que se tornará o principal.
     */
    public void trocarPokemonPrincipal(int indiceNaMochila) {
        if (indiceNaMochila >= 0 && indiceNaMochila < mochila.size()) {
            Pokemon novoPrincipal = mochila.get(indiceNaMochila);

            
            mochila.remove(indiceNaMochila);
            
            
            if (this.pokemonPrincipal != null) {
                mochila.add(this.pokemonPrincipal);
            }
            
            
            this.pokemonPrincipal = novoPrincipal;
            System.out.println(nome + " trocou para " + pokemonPrincipal.getNome());
        }
    }

    
    public void atualizarPontuacao() {
        pontuacao = 0;
        
        if (pokemonPrincipal != null) {
            pontuacao += pokemonPrincipal.getPontosDeExperiencia() + (pokemonPrincipal.getNivel() * 10);
        }
        
        for (Pokemon p : mochila) {
            pontuacao += p.getPontosDeExperiencia() + (p.getNivel() * 10);
        }
    }

    /**
     * Verifica se o Pokémon principal do treinador ainda tem energia para batalhar.
     * @return true se o Pokémon principal tem energia > 0.
     */
    public boolean temPokemonVivo() {
        return pokemonPrincipal != null && pokemonPrincipal.getEnergia() > 0;
    }

    
    public String getNome() { return nome; }
    public List<Pokemon> getMochila() { return mochila; }
    public int getPontuacao() { return pontuacao; }
    public Pokemon getPokemonPrincipal() { return pokemonPrincipal; }
}