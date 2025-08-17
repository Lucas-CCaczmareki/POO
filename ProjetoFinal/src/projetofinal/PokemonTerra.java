package projetofinal;

import java.util.Random;

public class PokemonTerra extends Pokemon {

    //Construtor (temp)
    public PokemonTerra(String nome) {
        setNome(nome);
    }

    @Override
    public int calcularDano(Pokemon oponente, int turnoAtual) {
        //Fórmula: dano = (força + random.nextInt(nivel + 1)) * fatorTipo + bônusHabilidade
        //Bonus de habilidade: Dano dobrado em turnos ímpares.
        int dano = 0;
        double fatorTipo = 1;
        //Calculando o vatorTipo com base nas vantagens e desvantagens
        if(oponente.getTipo().equals("Eletrico") || oponente.getTipo().equals("Floresta")) {
            fatorTipo = 1.5;
        } else if (oponente.getTipo().equals("Agua")) {
            fatorTipo = 0.5;
        }
        Random random = new Random();

        //A habilidade dos tipo terra faz com que seu poder seja dobrado nos turnos ímpares
        //Ou seja, ela entra no cálculo do dano
        int bonusHabilidade = 1;

        //A contagem dos turnos deve ser feita na classe que gere a partida.
        if(turnoAtual % 2 != 0) {
            bonusHabilidade = 2;
        }

        dano = (int) Math.round((getForca() + random.nextInt(getNivel() + 1)) * fatorTipo * bonusHabilidade);

        return dano;
    }
}
