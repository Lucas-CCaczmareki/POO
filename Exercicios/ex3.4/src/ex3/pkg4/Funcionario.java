package ex3.pkg4;

public class Funcionario {
    //Atributos
    private int id;
    private String nome;
    private double salario;
    private String departamento;

    //Construtores
    public Funcionario(int id, String nome, double salario, String departamento) {
        this.id = id;
        this.nome = nome;
        this.salario = salario;
        this.departamento = departamento;
    }

    public Funcionario(int id, String nome) {
        //Faz uma sobrecarga de métodos chamando o primeiro método construtor
        this(id, nome, 0.0, "Indefinido");
    }

    //Métodos
    public void aumentarSalario(double percentual) {
        this.salario += this.salario * percentual;
    }

    public void aumentarSalario(double valor, boolean fixo) {
        if(fixo) {
            //Se é pra aumentar num valor fixo, soma.
            this.salario += valor;
        } else {
            //Se não é um valor fixo, assume que é um percentual e manda bala.
            this.salario += this.salario * valor;
        }
    }

    //Getters
    public String getDepartamento() {
        return departamento;
    }
    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public double getSalario() {
        return salario;
    }

    //Setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setSalario(double salario) {
        if(salario >= 0) {
            this.salario = salario;
        } else {
            System.out.println("Valor de salário inválido!");
        }
        
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
