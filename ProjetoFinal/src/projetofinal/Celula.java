package projetofinal;

public class Celula {
    //Atributos
    private int linha;
    private int coluna;
    private Pokemon pkm;
    private boolean revelada;

    //Construtor
    public Celula(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.pkm = null;
        this.revelada = false;
    }

    //Métodos
    public boolean isRevelada() {
        return this.revelada;
    }
    
    public void revelar() {
        this.revelada = true;
    }

    //Getters e setters
    public int getColuna() {
        return coluna;
    }
    public int getLinha() {
        return linha;
    }
    public Pokemon getPkm() {
        return pkm;
    }
    public void setPkm(Pokemon pkm) {
        this.pkm = pkm;
    }
}
