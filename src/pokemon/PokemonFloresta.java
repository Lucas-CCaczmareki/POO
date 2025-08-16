package pokemon;

public class PokemonFloresta extends Pokemon {
    
    public PokemonFloresta(String nome) {
        super(nome, "Floresta", 25);
    }
    
    @Override
    public int calcularDano() {
        // Floresta: habilidade de regeneração (cura parte do dano ao atacar)
        int danoBase = (forca + random.nextInt(nivel + 1));
        int fatorTipo = 1;
        int bonusHabilidade = 0;
        
        // Regeneração ao atacar
        int cura = 10;
        curar(cura);
        bonusHabilidade = cura; // Bônus baseado na cura
        
        return danoBase * fatorTipo + bonusHabilidade;
    }
}
