package pokemon;

public class PokemonEletrico extends Pokemon {
    
    public PokemonEletrico(String nome) {
        super(nome, "Eletricidade", 40);
    }
    
    @Override
    public int calcularDano() {
        // Eletrico: chance de paralisar o adversário por 1 rodada
        int danoBase = (forca + random.nextInt(nivel + 1));
        int fatorTipo = 1;
        int bonusHabilidade = 0;
        
        // Chance de paralisar (30% de chance)
        if (random.nextInt(100) < 30) {
            bonusHabilidade = 15; // Bônus por paralisar
        }
        
        return danoBase * fatorTipo + bonusHabilidade;
    }
    
    public boolean tentarParalisar() {
        return random.nextInt(100) < 30;
    }
}
