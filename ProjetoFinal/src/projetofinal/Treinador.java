package projetofinal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um Treinador (o jogador humano).
 * Gerencia o time de Pokémon, a mochila para armazenamento, a pontuação e
 * as ações de jogo como capturar e trocar de Pokémon.
 * Implementa Serializable para permitir que o jogo seja salvo.
 */
public class Treinador implements Serializable {

    // CARACTERÍSTICAS DO TREINADOR
    private String nome;
    private List<Pokemon> timePokemon;
    private Pokemon pokemonPrincipal;
    private List<Pokemon> mochila;
    private int pontuacaoDoTime;

    public Treinador(String nome) {
        this.nome = nome;
        this.timePokemon = new ArrayList<>();
        this.mochila = new ArrayList<>();
        this.pontuacaoDoTime = 0;
        this.pokemonPrincipal = null;
    }

    /**
     * Adiciona o Pokémon inicial ao time do treinador.
     * Conforme o requisito, o time começa com apenas um Pokémon.
     * @param pokemon O primeiro Pokémon do treinador.
     */
    public void adicionarPokemonInicial(Pokemon pokemon) {
        if (this.timePokemon.isEmpty()) {
            pokemon.setSelvagem(false);
            this.timePokemon.add(pokemon);
            this.pokemonPrincipal = pokemon; // O primeiro é o principal
        }
    }

    /**
     * Ação de capturar um Pokémon. O Pokémon capturado é movido para a mochila.
     * @param pokemonCapturado O Pokémon que foi capturado.
     */
    public void capturarPokemon(Pokemon pokemonCapturado) {
        pokemonCapturado.setSelvagem(false);
        this.mochila.add(pokemonCapturado);
        System.out.println(this.nome + " capturou " + pokemonCapturado.getNome() + "!");
        atualizarPontuacao();
    }

    /**
     * ATIVIDADE 2: Trocar Pokémon Principal.
     * Permite ao treinador escolher outro Pokémon de sua mochila para ser o principal.
     * @param indiceNaMochila O índice do Pokémon na mochila a ser promovido.
     * @return true se a troca foi bem-sucedida, false caso contrário.
     */
    public boolean trocarPokemonPrincipal(int indiceNaMochila) {
        if (indiceNaMochila >= 0 && indiceNaMochila < mochila.size()) {
            Pokemon novoPrincipal = mochila.get(indiceNaMochila);

            // Troca: o antigo principal vai para a mochila, o novo sai da mochila e vai para o time
            mochila.remove(novoPrincipal);
            if (this.pokemonPrincipal != null) {
                mochila.add(this.pokemonPrincipal);
                timePokemon.remove(this.pokemonPrincipal);
            }
            timePokemon.add(novoPrincipal);
            this.pokemonPrincipal = novoPrincipal;

            System.out.println(this.nome + " agora tem " + novoPrincipal.getNome() + " como Pokémon principal!");
            return true;
        }
        return false;
    }

    /**
     * Atualiza a pontuação do time com base na experiência e nível de todos
     * os Pokémon que o treinador possui (time + mochila).
     */
    public void atualizarPontuacao() {
        int novaPontuacao = 0;
        // Soma a pontuação do time
        if (pokemonPrincipal != null) {
            novaPontuacao += pokemonPrincipal.getPontosDeExperiencia() + (pokemonPrincipal.getNivel() * 10);
        }
        // Soma a pontuação da mochila
        for (Pokemon p : this.mochila) {
            novaPontuacao += p.getPontosDeExperiencia() + (p.getNivel() * 10);
        }
        this.pontuacaoDoTime = novaPontuacao;
    }

    // --- Getters e Setters ---
    public String getNome() { return nome; }
    public Pokemon getPokemonPrincipal() { return pokemonPrincipal; }
    public List<Pokemon> getMochila() { return mochila; }
    public int getPontuacaoDoTime() { return pontuacaoDoTime; }
}