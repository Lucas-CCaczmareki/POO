package pokemon;

public class Celula {
    private Pokemon pokemon;
    private boolean revelada;
    private boolean reveladaPeloJogador;
    private int linha;
    private int coluna;
    
    public Celula(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.revelada = false;
        this.reveladaPeloJogador = false;
        this.pokemon = null;
    }
    
    public Pokemon getPokemon() {
        return pokemon;
    }
    
    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
    
    public boolean isRevelada() {
        return revelada;
    }
    
    public void setRevelada(boolean revelada) {
        this.revelada = revelada;
    }
    
    public boolean isReveladaPeloJogador() {
        return reveladaPeloJogador;
    }
    
    public void setReveladaPeloJogador(boolean reveladaPeloJogador) {
        this.reveladaPeloJogador = reveladaPeloJogador;
    }
    
    public boolean estaVazia() {
        return pokemon == null;
    }
    
    public int getLinha() {
        return linha;
    }
    
    public int getColuna() {
        return coluna;
    }
}
