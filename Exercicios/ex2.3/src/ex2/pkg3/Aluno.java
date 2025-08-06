package ex2.pkg3;

public class Aluno {
    //Atributos
    private String nome;
    private double nota;

    //Métodos

    //getters
    public String getNome() {
        return nome;
    }
    public double getNota() {
        return nota;
    }

    //Setter
    public void setNota(double nota) {
        if(nota > 10 || nota < 0) {
            System.out.println("Nota inválida!");
        } else {
            this.nota = nota;
        }
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
