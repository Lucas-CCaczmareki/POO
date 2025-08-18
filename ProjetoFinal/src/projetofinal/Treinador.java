package projetofinal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* Função do Serializable
 *  Serializable permite que os objetos dessa classe podem ser convertidos
 *  em uma sequência de bytes. É isso que permite salvar o estado dos pokémons em um arquivo
*/
    
/*
 * Treinador representa um dos jogadores do jogo. Essa classe contém os métodos e atributos
 * associados à um jogador.
 */
public class Treinador implements Serializable {
    
    //Atributos
    private String nome;
    private List<Pokemon> mochila; 
    private int pontuacao;
    private Pokemon pokemonPrincipal; 

    //Construtor
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
        //Verifica se o índice é válido
        if (indiceNaMochila >= 0 && indiceNaMochila < mochila.size()) {
            //Guarda o pokemon que vai se tornar o principal
            Pokemon novoPrincipal = mochila.get(indiceNaMochila);

            //Retira o pokemon da mochila
            mochila.remove(indiceNaMochila);
            
            //Coloca o pokemon principal atual na mochila
            if (this.pokemonPrincipal != null) {
                mochila.add(this.pokemonPrincipal);
            }
            
            //Troca o pokémon principal
            this.pokemonPrincipal = novoPrincipal;
            System.out.println(nome + " trocou para " + pokemonPrincipal.getNome());
        }
    }

    /*
     * Sempre que um treinador(jogador ou computador) faz uma ação, 
     * essa função zera e calcula a pontuação total dele.
     */
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

    //Getters e setters
    public String getNome() { return nome; }
    public List<Pokemon> getMochila() { return mochila; }
    public int getPontuacao() { return pontuacao; }
    public Pokemon getPokemonPrincipal() { return pokemonPrincipal; }
}