package ex7.pkg1;

public class Diretor extends Funcionario {
    //Atributos
    private int acoesEmpresa;

    //Construtor
    public Diretor(String nome, String matricula, double salarioBase, int acoesEmpresa) {
        super(nome, matricula, salarioBase);
        this.acoesEmpresa = acoesEmpresa;
    }

    //Métodos
    @Override
    public double calcularSalarioTotal(){
        int bonus = acoesEmpresa * 10;
        return getSalarioBase() + bonus;
    }

    public double calcularBonificacao(){
        return getSalarioBase() * 0.20;
    }
}
