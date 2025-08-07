package ex7.pkg1;

public class Atendente extends Funcionario {
    //Sem atributos novos

    //Construtor
    public Atendente(String nome, String matricula, double salarioBase) {
        super(nome, matricula, salarioBase);
    }

    //Métodos
    @Override
    public double calcularSalarioTotal() {
        return getSalarioBase() + 50.0;
    }

    public double calcularBonificacao() {
        return getSalarioBase() * 0.05;
    }
}
