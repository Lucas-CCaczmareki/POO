package pokemon;

import java.util.Random;

public class PokemonAgua extends Pokemon {

    /**
     * Construtor flexível para criar Pokémon de Água com stats diferentes.
     */
    public PokemonAgua(String nome, int energia, int forca, int nivel) {
        super(nome, "Água", energia, forca, nivel);
    }

    /**
     * Calcula o dano de ataque normal do Pokémon de Água.
     */
    @Override
    public int calcularDano(int numeroDoTurno) {
        // Fórmula de ataque padrão.
        return getForca() + new Random().nextInt(getNivel() * 5);
    }

    /**
     * HABILIDADE ESPECIAL: Redução de dano recebido.
     * Este método é sobrescrito da classe pai para aplicar a habilidade defensiva.
     * @param dano O dano original do ataque do oponente.
     */
    @Override
    public void receberDano(int dano) {
    // A lógica de redução agora é controlada pelo Jogo, então usamos o método padrão
    super.receberDano(dano);
}
}