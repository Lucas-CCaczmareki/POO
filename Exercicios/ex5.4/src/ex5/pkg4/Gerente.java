package ex5.pkg4;

public class Gerente extends Funcionario{
    //atributos
    private double bonus;
    
    //Construtor
    public Gerente(String nome, String matricula, double salarioBase, double bonus){
        super(nome, matricula, salarioBase);
        this.bonus = bonus;
    }

    //Métodos
    @Override
    public double calcularSalarioTotal(){
        setSalarioBase(getSalarioBase() + bonus);
        return getSalarioBase();
    }

}
