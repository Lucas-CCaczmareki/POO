package projetofinal;

import java.util.Random;

public class PokemonEletrico extends Pokemon {

    private static final double CHANCE_PARALISAR = 0.25; // 25%

    public PokemonEletrico(String nome, int energia, int forca, int nivel) {
        super(nome, "Elétrico", energia, forca, nivel);
    }

    /**
     * Calcula o dano de ataque normal. A habilidade de paralisar é um efeito secundário.
     */
    @Override
    public int calcularDano(int numeroDoTurno) {
        return getForca() + new Random().nextInt(getNivel() * 5);
    }

    /**
     * [cite_start]HABILIDADE ESPECIAL: Chance de paralisar o adversário por 1 rodada. [cite: 90]
     * A classe Jogo deve chamar este método após um ataque para verificar o efeito.
     * @return true se conseguiu paralisar, false caso contrário.
     */
    public boolean tentarParalisar() {
        return new Random().nextDouble() < CHANCE_PARALISAR;
    }
}