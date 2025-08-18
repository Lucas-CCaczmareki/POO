package projetofinal;

import java.util.Random;

public class PokemonTerra extends Pokemon {

    //Construtor
    public PokemonTerra(String nome, int energia, int forca, int nivel) {
        super(nome, "Terra", energia, forca, nivel);
    }

    /**
     * @param numeroDoTurno O número do turno atual, vindo da classe Jogo.
     * Esse parâmetro é necessário para implementar a habilidade bônus dos tipo terra:
     *    * O dano do seu ataque é dobrado se estamos em turno ímpar
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