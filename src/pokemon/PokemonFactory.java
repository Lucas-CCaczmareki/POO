package pokemon;

public class PokemonFactory {
    
    public static Pokemon criarPokemon(String nome) {
        switch (nome.toLowerCase()) {
            case "bulbasaur":
                return new PokemonFloresta("Bulbasaur");
            case "caterpie":
                return new PokemonFloresta("Caterpie");
            case "diglet":
                return new PokemonTerra("Diglet");
            case "magnemite":
                return new PokemonEletrico("Magnemite");
            case "paras":
                return new PokemonAgua("Paras");
            case "pikachu":
                return new PokemonEletrico("Pikachu");
            case "sandshrew":
                return new PokemonTerra("Sandshrew");
            case "squirtle":
                return new PokemonAgua("Squirtle");
            default:
                throw new IllegalArgumentException("Pokémon não encontrado: " + nome);
        }
    }
    
    public static String[] getPokemonsDisponiveis() {
        return new String[] {
            "Bulbasaur", "Caterpie", "Diglet", "Magnemite",
            "Paras", "Pikachu", "Sandshrew", "Squirtle"
        };
    }
    
    public static String getTipoPokemon(String nome) {
        switch (nome.toLowerCase()) {
            case "bulbasaur":
            case "caterpie":
                return "Floresta";
            case "diglet":
            case "sandshrew":
                return "Terra";
            case "magnemite":
            case "pikachu":
                return "Eletrico";
            case "paras":
            case "squirtle":
                return "Agua";
            default:
                return "Desconhecido";
        }
    }
}
