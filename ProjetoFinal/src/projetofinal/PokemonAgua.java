package projetofinal;

import java.util.Random;

public class PokemonAgua extends Pokemon {
    //Recebe todos atributos que um pokemon tem

    //Construtor (temp)
    public PokemonAgua(String nome) {
        super(nome, "Agua", 100, 15, 5);
    }

    //É aqui que a habilidade dos pokemon água aparecem
    @Override
    public void receberDano(int dano, String ambiente) {
        if(ambiente.equals("Agua")) {
            this.setEnergia(this.getEnergia() - dano);
        } else {
            dano *= 0.7; //reduz o dano em 30%
            this.setEnergia(this.getEnergia() - dano);
        }
    }

    @Override
    public int calcularDano(Pokemon oponente, int turnoAtual) {
        //Fórmula: dano = (força + random.nextInt(nivel + 1)) * fatorTipo + bônusHabilidade
        int dano = 0;
        double fatorTipo = 1;

        if(oponente.getTipo().equals("Terra")) {
            fatorTipo = 1.5;
        } else if (oponente.getTipo().equals("Floresta")) {
            fatorTipo = 0.5;
        }

        Random random = new Random();

        //Bonus de habilidade não influencia no dano dos pokemon água
        dano = (int) Math.round((dano + random.nextInt(this.getNivel() + 1)) * fatorTipo);
        return dano;
    }
}
