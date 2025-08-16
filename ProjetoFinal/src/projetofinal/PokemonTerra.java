package projetofinal;

import java.util.Random;

public class PokemonTerra extends Pokemon {

    public PokemonTerra(String nome, int energia, int forca, int nivel) {
        super(nome, "Terra", energia, forca, nivel);
    }

    @Override
    public int calcularDano(int numeroDoTurno) {
        int danoBase = new Random().nextInt(getForca() + 1) * getNivel() + getPontosDeExperiencia();
        if (numeroDoTurno % 2 != 0) {
            System.out.println(getNome() + " usa sua fúria em um turno ímpar! Dano dobrado!");
            return danoBase * 2;
        }
        return danoBase;
    }
}
