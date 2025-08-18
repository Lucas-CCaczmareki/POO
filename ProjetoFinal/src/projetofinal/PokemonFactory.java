package projetofinal;

/*
 * Essa classe implementa o padrão de projeto Factory
 * Onde não precisamos saber como instanciar cada tipo de pokémon, isso é padronizado
 *  dentro dessa classe.
 * 
 * Isso facilita a manutenção, expansão e evita duplicação de código.
 * Centraliza a lógica de criação de pokémons
 */

public class PokemonFactory {
    
    /**
     * O método estático serve para que possamos utilizá-la sem instanciar um objeto dessa classe.
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
                return new PokemonFloresta("Paras", 90, 18, 4);
            default:
                throw new IllegalArgumentException("Pokémon não encontrado na Factory: " + nome);
        }
    }
    
    /**
     * @return um array de Strings com os nomes.
     */
    public static String[] getPokemonsDisponiveis() {
        return new String[] {
            "Bulbasaur", "Caterpie", "Diglet", "Magnemite",
            "Paras", "Pikachu", "Sandshrew", "Squirtle"
        };
    }
}