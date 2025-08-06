/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package treinoencapsulamento;

/**
 *
 * @author lucas
 * Classe abstrata "Conta", serve como um rascunho genérico, onde
 * as suas filhas (subclasses) irão herdar seu corpo. Essa classe não pode ser instanciada diretamente
 */
public abstract class Conta {
    //Atributos
    //passam a serem protected, para que as classes filhas (tipos de conta) possam usar ele
    protected String proprietario;
    protected int codigo;
    protected double saldo;
    protected Banco banco;    //referência para o banco ao qual a conta pertence

    //Construtores
    // O construtor agora recebe o objeto Banco para criar o vínculo
    public Conta(String proprietario, int codigo, Banco banco) {
        this.proprietario = proprietario;
        this.codigo = codigo;
        this.banco = banco; //guarda a referência do banco
        this.saldo = 0.0;
    }

    //Métodos concretos, que serão herdados por TODAS subclasses de conta.
    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("Deposito de R$" + valor + " realizado na conta " + this.codigo + "com sucesso!");
        }
    }

    //Cada subclasse terá sua própria maneira de sacar
    public void sacar(double valor) {
        if( valor <= 0 ) {
            System.out.println("Valor de saque deve ser positivo.");
            return;
        }
        
        if( this.saldo >= valor ) {
            this.saldo -= valor;
            System.out.println("Saque padrão de R$" + valor + " realizado com sucesso");
        } else {
            System.out.println("Saque não realizado. Saldo insuficiente.");
        }
    }

    //Métodos abstratos
    //São métodos sem corpos, que definem uma obrigação para as classes filhas implementarem.
    public abstract void imprimirExtrato();

    // Getters para os atributos
    public String getProprietario() {
        return this.proprietario;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public String getNomeDoBanco() {
        return this.banco.getNome();
    }

}
