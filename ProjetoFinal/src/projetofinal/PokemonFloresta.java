package projetofinal;

/**
 * Representa um Pokémon do tipo Floresta.
 * [cite_start]Habilidade: regeneração de parte do dano ao atacar. [cite: 88]
 */
public class PokemonFloresta extends Pokemon {
    @Override
    public int calcularDano() {
        // Lógica de ataque que inclui regeneração.
        return getForca() + getNivel();
    }
}
