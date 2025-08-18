package projetofinal;

import java.util.Random;

public class PokemonEletrico extends Pokemon {

    //Atributos
    private static final double CHANCE_PARALISAR = 0.25; // 25%

    //Construtor
    public PokemonEletrico(String nome, int energia, int forca, int nivel) {
        super(nome, "Elétrico", energia, forca, nivel);
    }

    //Métodos
    /*
     * Sobreescreve o método de calcular dano da interface
     */
    @Override
    public int calcularDano(int numeroDoTurno) {
        return getForca() + new Random().nextInt(getNivel() * 5);
    }

    /**
     * Implementação da habilidade bônus dos tipo elétrico
     * @return true se conseguiu paralisar, false caso contrário.
     */
    public boolean tentarParalisar() {
        return new Random().nextDouble() < CHANCE_PARALISAR;
    }
}