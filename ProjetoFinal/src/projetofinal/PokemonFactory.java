package projetofinal;

/**
 * [DESAFIO: 1 PONTO EXTRA!] Implementa o padrão de projeto Factory Method.
 * Esta classe centraliza a criação de todos os objetos Pokémon do jogo,
 * facilitando a manutenção e a adição de novos Pokémon no futuro.
 */
public class PokemonFactory {
    
    /**
     * Cria e retorna uma instância de um Pokémon específico com base no nome.
     * Define atributos padrão (energia, força, nível) para cada um.
     * @param nome O nome do Pokémon a ser criado.
     * @return Uma instância da subclasse de Pokémon correspondente.
     */
    public static Pokemon criarPokemon(String nome) {
        switch (nome.toLowerCase()) {
            case "squirtle":
                return new PokemonAgua("Squirtle", 100, 25, 5);
            case "bulbasaur":
                return new PokemonFloresta("Bulbasaur", 110, 22, 5);
            case "sandshrew":
                return new PokemonTerra("Sandshrew", 95, 26, 5);
            case "pikachu":
                return new PokemonEletrico("Pikachu", 90, 28, 5);
            case "caterpie":
                return new PokemonFloresta("Caterpie", 80, 15, 3);
            case "diglet":
                return new PokemonTerra("Diglet", 85, 20, 4);
            case "magnemite":
                return new PokemonEletrico("Magnemite", 80, 22, 4);
            case "paras":
                // CORRIGIDO: Paras é do tipo Planta/Inseto, representado por Floresta.
                return new PokemonFloresta("Paras", 90, 18, 4);
            default:
                throw new IllegalArgumentException("Pokémon não encontrado na Factory: " + nome);
        }
    }
    
    /**
     * Retorna uma lista com os nomes de todos os Pokémon que a factory pode criar.
     * @return um array de Strings com os nomes.
     */
    public static String[] getPokemonsDisponiveis() {
        return new String[] {
            "Bulbasaur", "Caterpie", "Diglet", "Magnemite",
            "Paras", "Pikachu", "Sandshrew", "Squirtle"
        };
    }
}