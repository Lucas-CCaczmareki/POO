package ex5.pkg4;

public class Funcionario {
    //Atributos
    private String nome;
    private String matricula;
    private double salarioBase;

    //Construtor
    public Funcionario(String nome, String matricula, double salarioBase) {
        this.nome = nome;
        this.matricula = matricula;
        this.salarioBase = salarioBase;
    }

    //Métodos
    public double calcularSalarioTotal() {
        return this.salarioBase;
    }

    //Getters e setters
    public String getMatricula() {
        return matricula;
    }
    public String getNome() {
        return nome;
    }
    public double getSalarioBase() {
        return salarioBase;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }
}
