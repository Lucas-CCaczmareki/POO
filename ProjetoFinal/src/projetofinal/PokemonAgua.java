package projetofinal;

import java.util.Random;

public class PokemonAgua extends Pokemon {

    public PokemonAgua(String nome, int energia, int forca, int nivel) {
        super(nome, "Água", energia, forca, nivel);
    }

    @Override
    public int calcularDano(int numeroDoTurno) {
        return new Random().nextInt(getForca() + 1) * getNivel() + getPontosDeExperiencia();
    }

    /**
     * [cite_start]Habilidade especial: Redução de dano recebido em ambientes adversos. [cite: 91]
     * Sobrescrevemos o método da classe pai para aplicar a redução de 20%.
     */
    @Override
    public void receberDano(int dano) {
        int danoReduzido = (int) (dano * 0.8); // Recebe apenas 80% do dano
        System.out.println(getNome() + " resistiu ao ataque com sua defesa de água!");
        super.receberDano(danoReduzido);
    }
}