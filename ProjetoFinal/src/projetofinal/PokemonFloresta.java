package projetofinal;

import java.util.Random;

public class PokemonFloresta extends Pokemon {

    //Construtor
    public PokemonFloresta(String nome, int energia, int forca, int nivel) {
        super(nome, "Floresta", energia, forca, nivel);
    }

    /*
     * Sobreescreve o método da interface aplicando a habilidade bônus especial do tipo floresta
     * A habilidade do tipo floresta faz com que o pokémon que atacou cure 20% do dano causado.
     */
    @Override
    public int calcularDano(int numeroDoTurno) {
        
        //Calcula a cura e se cura.
        int cura = (int) (getForca() * 0.2); 
        this.curar(cura); 
        
        return getForca() + new Random().nextInt(getNivel() * 5);
    }
}