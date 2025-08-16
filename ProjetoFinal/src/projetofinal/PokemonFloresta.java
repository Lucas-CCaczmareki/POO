package projetofinal;

import java.util.Random;

public class PokemonFloresta extends Pokemon {
    @Override
    public int calcularDano(Pokemon oponente, int turnoAtual) {
        //Fórmula: dano = (força + random.nextInt(nivel + 1)) * fatorTipo + bônusHabilidade
        
        double fatorTipo = 1.0; //valor padrão
        if(oponente.getTipo().equals("Agua")) {
            fatorTipo = 1.5;
        } else if (oponente.getTipo().equals("Eletrico")) {
            fatorTipo = 0.5;
        }
        Random random = new Random();
        
        //Calcula o dano com base no aumento do fatorTipo
        int dano = (int) Math.round((this.getForca() + random.nextInt(this.getNivel() + 1)) * fatorTipo);

        //o floresta não tem bonus de habilidade adicionado ao dano, ele se cura com base no dano
        double bonusHabilidade = dano * 0.3; //cura 30% do dano
        setEnergia(getEnergia() + (int)bonusHabilidade); //arredonda o tanto que cura pra um inteiro


        return dano;
    }
}
