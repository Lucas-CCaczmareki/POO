package ex4.pkg4;

/**
 *
 * @author lucas
 */
public class Aluno {
    //Atributos
    private String nome;
    private String matricula;
    private double nota;

    //Construtor
    public Aluno(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    //Métodos
    public void atribuirNota(double nota) {
        if(nota < 0) {
            System.out.println("Nota digitada inválida!");
        } else if (nota < 10) {
            System.out.println("Nota atribuida com sucesso");
        } else {
            System.out.println("Nota digitada inválida!");
        }
    }

    //Getters e setters
    public String getMatricula() {
        return matricula;
    }
    public String getNome() {
        return nome;
    }
    public double getNota() {
        return nota;
    }
}
