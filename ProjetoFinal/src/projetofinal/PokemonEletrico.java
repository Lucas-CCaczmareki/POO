package projetofinal;

/**
 * Representa um Pokémon do tipo Elétrico.
 * [cite_start]Habilidade: chance de paralisar o adversário por 1 rodada. [cite: 90]
 */
public class PokemonEletrico extends Pokemon {
    @Override
    public int calcularDano() {
        // Lógica de ataque que inclui a chance de paralisar.
        return getForca() + getNivel();
    }
}
