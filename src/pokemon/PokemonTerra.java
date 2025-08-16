package pokemon;

public class PokemonTerra extends Pokemon {
    private static int turnoGlobal = 0;
    
    public PokemonTerra(String nome) {
        super(nome, "Terra", 35);
    }
    
    @Override
    public int calcularDano() {
        // Terra: ataque com força dobrada em turno ímpar
        int danoBase = (forca + random.nextInt(nivel + 1));
        int fatorTipo = 1;
        int bonusHabilidade = 0;
        
        // Ataque dobrado em turno ímpar
        if (turnoGlobal % 2 == 1) {
            fatorTipo = 2;
        }
        
        return danoBase * fatorTipo + bonusHabilidade;
    }
    
    public static void incrementarTurno() {
        turnoGlobal++;
    }
}
