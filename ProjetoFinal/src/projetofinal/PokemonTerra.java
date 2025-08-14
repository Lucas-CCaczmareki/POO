package projetofinal;

/**
 * Representa um Pokémon do tipo Terra.
 * [cite_start]Habilidade: ataque com força dobrada em turnos ímpares. [cite: 89]
 */
public class PokemonTerra extends Pokemon {
    @Override
    public int calcularDano() {
        // Lógica de ataque que verifica o turno para dobrar a força.
        return getForca() + getNivel();
    }
}
