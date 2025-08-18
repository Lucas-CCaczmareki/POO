package projetofinal;

import java.util.Random;

public class PokemonAgua extends Pokemon {

    //Construtor
    public PokemonAgua(String nome, int energia, int forca, int nivel) {
        super(nome, "Água", energia, forca, nivel);
    }

    /*
     * Reescreve o método de calcularDano para a especialidade do pokémon de água.
     * A habilidade bônus dos pokémon tipo água vai ser tratada na classe jogo
     * Ela é responsável por diminuir o dano sofrido em 20% se estiver em terreno adverso
     */
    @Override
    public int calcularDano(int numeroDoTurno) {
        return getForca() + new Random().nextInt(getNivel() * 5);
    }

    
}