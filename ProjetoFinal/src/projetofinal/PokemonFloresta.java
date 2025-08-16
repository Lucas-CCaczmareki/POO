package projetofinal;

import java.util.Random;

public class PokemonFloresta extends Pokemon {

    public PokemonFloresta(String nome, int energia, int forca, int nivel) {
        super(nome, "Floresta", energia, forca, nivel);
    }

    @Override
    public int calcularDano(int numeroDoTurno) {
        int cura = (int) (getForca() * 0.2); // Cura 20% do valor da sua força
        setEnergia(Math.min(getEnergiaMaxima(), getEnergia() + cura));
        System.out.println(getNome() + " usou a regeneração e curou " + cura + " de vida!");

        // Fórmula de ataque exemplo: random(força) * nível + experiência
        int danoBase = new Random().nextInt(getForca() + 1);
        return danoBase * getNivel() + getPontosDeExperiencia();
    }
}


