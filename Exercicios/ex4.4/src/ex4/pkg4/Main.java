package ex4.pkg4;

public class Main {
    public static void main(String[] args) {
        Aluno a1 = new Aluno("Lucas Caczmareki", "241100");
        Aluno a2 = new Aluno("Shadow the hedgehog", "100001");
        Aluno a3 = new Aluno("Sonic the hedgehog", "011110");

        Disciplina d1 = new Disciplina("Corrida", "000");

        Professor p1 = new Professor("Dr Robotnik Eggman", "000000", "Comedores de pipoca");

        Turma t1 = new Turma("001", p1, d1);

        t1.addAluno(a1);
        t1.addAluno(a2);
        t1.addAluno(a3);

        t1.listarAlunos();
    }
    
}
