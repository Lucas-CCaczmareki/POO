package projetofinal;

import java.util.Random;

public class PokemonTerra extends Pokemon {

    public PokemonTerra(String nome, int energia, int forca, int nivel) {
        super(nome, "Terra", energia, forca, nivel);
    }

    /**
     * [cite_start]HABILIDADE ESPECIAL: Ataque com força dobrada em turno ímpar. [cite: 89]
     * @param numeroDoTurno O número do turno atual, vindo da classe Jogo.
     */
    @Override
    public int calcularDano(int numeroDoTurno) {
        int danoBase = getForca() + new Random().nextInt(getNivel() * 5);

        if (numeroDoTurno % 2 != 0) {
            return danoBase * 2;
        }
        return danoBase;
    }
}