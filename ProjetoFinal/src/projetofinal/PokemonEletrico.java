package projetofinal;
import java.util.Random;

public class PokemonEletrico extends Pokemon {

    private static final double CHANCE_PARALISAR = 0.25; // 25%

    public PokemonEletrico(String nome, int energia, int forca, int nivel) {
        super(nome, "Elétrico", energia, forca, nivel);
    }

    @Override
    public int calcularDano(int numeroDoTurno) {
        return new Random().nextInt(getForca() + 1) * getNivel() + getPontosDeExperiencia();
    }

    /**
     * [cite_start]Habilidade especial: Chance de paralisar o adversário por 1 rodada. [cite: 90]
     * @return true se conseguiu paralisar, false caso contrário.
     */
    public boolean tentarParalisar() {
        return new Random().nextDouble() < CHANCE_PARALISAR;
    }
}
