package pokemon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um Treinador no jogo (jogador ou computador).
 * Esta versão foi corrigida para gerenciar corretamente o Pokémon principal
 * e a mochila, de acordo com as regras do documento de trabalho.
 */
public class Treinador implements Serializable {
    private String nome;
    private List<Pokemon> mochila; // Armazena todos os Pokémon que não estão em batalha
    private int pontuacao;
    private Pokemon pokemonPrincipal; // O único Pokémon ativo no "time"

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
     * CORRIGIDO: Captura um Pokémon e o coloca diretamente na mochila.
     * @param pokemon O Pokémon capturado.
     */
    public void capturarPokemon(Pokemon pokemon) {
        pokemon.setSelvagem(false);
        this.mochila.add(pokemon);
        System.out.println(nome + " capturou " + pokemon.getNome() + "!");
        atualizarPontuacao();
    }

    /**
     * CORRIGIDO: Troca o Pokémon principal por um da mochila.
     * O antigo Pokémon principal é movido para a mochila.
     * @param indiceNaMochila O índice do Pokémon na mochila que se tornará o principal.
     */
    public void trocarPokemonPrincipal(int indiceNaMochila) {
        if (indiceNaMochila >= 0 && indiceNaMochila < mochila.size()) {
            Pokemon novoPrincipal = mochila.get(indiceNaMochila);

            // Tira o novo principal da mochila
            mochila.remove(indiceNaMochila);
            
            // Coloca o antigo principal na mochila (se existir)
            if (this.pokemonPrincipal != null) {
                mochila.add(this.pokemonPrincipal);
            }
            
            // Define o novo Pokémon como principal
            this.pokemonPrincipal = novoPrincipal;
            System.out.println(nome + " trocou para " + pokemonPrincipal.getNome());
        }
    }

    /**
     * CORRIGIDO: Calcula a pontuação com base em TODOS os Pokémon do treinador.
     */
    public void atualizarPontuacao() {
        pontuacao = 0;
        // Adiciona a pontuação do Pokémon principal
        if (pokemonPrincipal != null) {
            pontuacao += pokemonPrincipal.getPontosDeExperiencia() + (pokemonPrincipal.getNivel() * 10);
        }
        // Adiciona a pontuação dos Pokémon na mochila
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

    // --- Getters ---
    public String getNome() { return nome; }
    public List<Pokemon> getMochila() { return mochila; }
    public int getPontuacao() { return pontuacao; }
    public Pokemon getPokemonPrincipal() { return pokemonPrincipal; }
}