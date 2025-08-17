package projetofinal;

public class Tabuleiro {
    //Atributos
    private Celula[][] grade;
    private int tamanhoN;

    //Construtor
    public Tabuleiro() {
        this.tamanhoN = 8;
        this.grade = new Celula[this.tamanhoN][this.tamanhoN];
        for(int l = 0; l < tamanhoN; l++) {
            for(int c = 0; c < tamanhoN; c++) {
                grade[l][c] = new Celula(l, c);
            }
        }
    }

    //Métodos
    public void posicionarPokemon(Pokemon pkm, int linha, int coluna) {
        if (linha >= 0 && linha < tamanhoN && coluna >= 0 && coluna < tamanhoN) {
            this.grade[linha][coluna].setPkm(pkm);
        }
    }

    public String getRegiaoDaCelula(int linha, int coluna) {
        int metade = tamanhoN / 2;

        if (linha < metade && coluna < metade) {
            return "Agua";
        } else if (linha < metade && coluna >= metade) {
            return "Floresta";
        } else if (linha >= metade && coluna < metade) {
            return "Terra";
        } else {
            return "Eletricidade";
        }
    }

    //Getters e setters
    public Celula[][] getGrade() {
        return grade;
    }

    public Celula getCelula(int linha, int coluna) {
        return this.grade[linha][coluna];
    }
    


}
