package projetofinal;

import java.util.Random;

public class PokemonEletrico extends Pokemon {
    
    //Construtor (temp)
    public PokemonEletrico(String nome) {
        // Chama o construtor da classe pai (Pokemon) com os atributos base
        // super(nome, tipo, energia, forca, nivel);
        super(nome, "Eletrico", 100, 15, 5); // Ex: Pikachu Lvl 5 com 100 HP e 15 de Força
    }

    @Override
    public int calcularDano(Pokemon oponente, int turnoAtual) {
        //Fórmula: dano = (força + random.nextInt(nivel + 1)) * fatorTipo + bônusHabilidade
        double fatorTipo = 1;

        //Calcula o fatorTipo com base nas vantagens/desvantagens elementais
        if(oponente.getTipo().equals("Agua")) {
            fatorTipo = 1.5;
        } else if (oponente.getTipo().equals("Terra")) {
            fatorTipo = 0.5;
        }

        Random random = new Random();

        //o bonus de habilidade não afeta o dano
        int dano = (int) Math.round((this.getForca() + random.nextInt(getNivel() + 1)) * fatorTipo);

        //Define se o ataque do pokemon eletrico vai paralizar ou não
        if(random.nextInt(6) == 1) {
            oponente.setStatus("Paralisado");
        }

        return dano;
    }
}
