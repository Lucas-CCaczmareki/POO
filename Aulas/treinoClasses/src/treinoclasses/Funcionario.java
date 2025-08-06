/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package treinoclasses;

/**
 *
 * @author lucas
 */
public class Funcionario {
    //Atributos
    private String nome;
    private String departamento;
    private double salario;
    private String dataEntrada;
    private String rg;

    //Método construtor
    //Para definir um método construtor ele:
        //Não deve ter parâmetros de retorno.
        //Deve ter exatamente o mesmo nome da classe.
    public Funcionario(String nome, String departamento, double salario, String dataEntrada, String rg ) {
        this.nome = nome;
        this.departamento = departamento;
        this.salario = salario;
        this.dataEntrada = dataEntrada;
        this.rg = rg;
    }

    //Métodos
    public void recebeAumento(double aumento) {
        this.salario += aumento;
    }

    public double calculaGanhoAnual() {
        return this.salario * 12;
    }

    public void mostraDados() {
        System.out.println("---------- Funcionario ----------\n" + this.nome + "\n"  + this.departamento + "\n" + this.salario + "\n" + this.dataEntrada + "\n" + this.rg + "\n" + "------------------------------");
    }
}
