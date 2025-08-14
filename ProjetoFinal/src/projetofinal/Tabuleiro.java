package projetofinal;

/**
 * [cite_start]Representa a área do jogo, dividida em células. [cite: 45]
 * É responsável por gerenciar a posição dos Pokémon e as regiões.
 */
public class Tabuleiro {
    private Celula[][] grade;
    private int tamanhoN;

    public Tabuleiro(int tamanhoN) {
        this.tamanhoN = tamanhoN;
        this.grade = new Celula[tamanhoN][tamanhoN];
        // Inicializa cada célula da grade.
        for (int i = 0; i < tamanhoN; i++) {
            for (int j = 0; j < tamanhoN; j++) {
                grade[i][j] = new Celula(i, j);
            }
        }
    }

    // Getters e Setters
    public Celula[][] getGrade() {
        return grade;
    }

    public void setGrade(Celula[][] grade) {
        this.grade = grade;
    }

    public int getTamanhoN() {
        return tamanhoN;
    }

    public void setTamanhoN(int tamanhoN) {
        this.tamanhoN = tamanhoN;
    }
}
