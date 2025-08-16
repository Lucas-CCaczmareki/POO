package pokemon;

public class PokemonAgua extends Pokemon {
    
    public PokemonAgua(String nome) {
        super(nome, "Água", 30);
    }
    
    @Override
    public int calcularDano() {
        // Água: redução de dano recebido em ambientes adversos
        int danoBase = (forca + random.nextInt(nivel + 1));
        int fatorTipo = 1;
        int bonusHabilidade = 0;
        
        // Redução de dano em ambientes adversos (implementação simplificada)
        if (energia < 50) {
            bonusHabilidade = 5; // Bônus defensivo quando energia baixa
        }
        
        return danoBase * fatorTipo + bonusHabilidade;
    }
}
