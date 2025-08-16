package pokemon;

public class PokemonAgua extends Pokemon {
    
    public PokemonAgua(String nome) {
        super(nome, "Agua", 30);
    }
    
    @Override
    public int calcularDano() {
        // Agua: reducao de dano recebido em ambientes adversos
        int danoBase = (forca + random.nextInt(nivel + 1));
        int fatorTipo = 1;
        int bonusHabilidade = 0;
        
        // Reducao de dano em ambientes adversos (implementacao simplificada)
        if (energia < 50) {
            bonusHabilidade = 5; // Bonus defensivo quando energia baixa
        }
        
        return danoBase * fatorTipo + bonusHabilidade;
    }
}
