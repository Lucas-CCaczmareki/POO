package ex4.pkg4;

import java.util.ArrayList;

public class Turma {
    //Atributos
    private String idTurma;
    private Professor prof;
    private Disciplina disciplina;
    private ArrayList<Aluno> alunos;
    
    //Construtor
    public Turma(String idTurma, Professor prof, Disciplina disciplina) {
        this.idTurma = idTurma;
        this.prof = prof;
        this.disciplina = disciplina;
        alunos = new ArrayList<Aluno>();
    }

    //Métodos
    public void addAluno(Aluno aluno){
        alunos.add(aluno);
    }

    public void listarAlunos(){
        System.out.println("--------------- Turma " + this.idTurma + " ---------------");
        for(int i = 0; i < alunos.size(); i++) {
            System.out.println("- \t" + alunos.get(i).getNome());
        }
        System.out.println("---------------------------------------------");
    }




    //Getters e setters
    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }
    public Disciplina getDisciplina() {
        return disciplina;
    }
    public String getIdTurma() {
        return idTurma;
    }
    public Professor getProf() {
        return prof;
    }
    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }
    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
    public void setIdTurma(String idTurma) {
        this.idTurma = idTurma;
    }
    public void setProf(Professor prof) {
        this.prof = prof;
    }
}
