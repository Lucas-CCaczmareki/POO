package ex9.pkg4;

public class Aluno {
    //Atributos
    private String nome;
    private String matricula;
    private double nota;

    //Construtor
    public Aluno (String nome, String matricula, double nota) {
        if(nome == null || nome == "") {
            throw new NomeInvalidoException();
        } else {
            this.nome = nome;
        }

        if(matricula.length() > 6 || matricula.length() < 6) {
            throw new MatriculaInvalidaException();
        } else {
            this.matricula = matricula;
        }
        if(nota < 0 || nota > 10) {
            throw new NotaInvalidaException();
        } else {
            this.nota = nota;
        }
    }
}
