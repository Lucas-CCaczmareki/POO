package projetofinal;

/**
 * Representa um Pokémon do tipo Água.
 * [cite_start]Habilidade: redução de dano recebido. [cite: 91]
 */
public class PokemonAgua extends Pokemon {
    @Override
    public int calcularDano() {
        // Exemplo de implementação do cálculo de dano.
        // dano = (força + random.nextInt(nivel + 1)) * fatorTipo + bônusHabilidade [cite: 147]
        return getForca() + getNivel();
    }
}
