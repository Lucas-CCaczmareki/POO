package projetofinal;

import java.util.Random;

public class PokemonFloresta extends Pokemon {

    public PokemonFloresta(String nome, int energia, int forca, int nivel) {
        super(nome, "Floresta", energia, forca, nivel);
    }

    
    @Override
    public int calcularDano(int numeroDoTurno) {
        
        int cura = (int) (getForca() * 0.2); 
        this.curar(cura); //

        return getForca() + new Random().nextInt(getNivel() * 5);
    }
}