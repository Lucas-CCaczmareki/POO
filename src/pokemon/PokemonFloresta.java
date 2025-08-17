package pokemon;

import java.util.Random;

public class PokemonFloresta extends Pokemon {

    public PokemonFloresta(String nome, int energia, int forca, int nivel) {
        super(nome, "Floresta", energia, forca, nivel);
    }

    /**
     * [cite_start]HABILIDADE ESPECIAL: Regeneração (cura parte do dano ao atacar). [cite: 88]
     */
    @Override
    public int calcularDano(int numeroDoTurno) {
        // Lógica de cura que acontece durante o ataque
        int cura = (int) (getForca() * 0.2); // Cura 20% do valor da sua força
        this.curar(cura); // Usa o método de cura da classe pai
        System.out.println(getNome() + " usou a regeneração e curou " + cura + " de vida!");

        // Calcula e retorna o dano do ataque
        return getForca() + new Random().nextInt(getNivel() * 5);
    }
}