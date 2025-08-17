package projetofinal;

import java.util.Random;

public class PokemonAgua extends Pokemon {

    public PokemonAgua(String nome, int energia, int forca, int nivel) {
        super(nome, "Água", energia, forca, nivel);
    }

    @Override
    public int calcularDano(int numeroDoTurno) {
        return getForca() + new Random().nextInt(getNivel() * 5);
    }

    
}