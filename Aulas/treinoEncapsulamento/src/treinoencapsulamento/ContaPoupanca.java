/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package treinoencapsulamento;

/**
 *
 * @author lucas
 */
public class ContaPoupanca extends Conta {
    
    //Construtor que chama o construtor da super classe
    public ContaPoupanca(String proprietario, int codigo, Banco banco) {
        super(proprietario, codigo, banco);
    }

    //Método único da conta poupança
    public void renderJuros(double taxa) {
        this.saldo += this.saldo * taxa;
        System.out.println("[Conta poupança] Juros aplicados; Novo saldo: R$" + this.saldo);
    }

    //Acho eu que aqui nem precisaria fazer um polimorfismo, pq só muda o print com relação a classe "Conta"
    @Override
    public void sacar(double valor) {
        if (valor > 0 && this.saldo >= valor) {
            this.saldo -= valor;
            System.out.println("[Conta Poupança] Saque de R$" + valor + " realizado.");
        } else {
            System.out.println("[Conta Poupança] Saque não realizado. Saldo insuficiente.");
        }
    }
    //Implementação obrigatória do método abstrato
    @Override
    public void imprimirExtrato(){
        System.out.println("--- Extrano Conta Poupança ---");
        System.out.println("Proprietário: " + this.proprietario);
        System.out.println("Banco: " + this.banco.getNome());
        System.out.println("Saldo: R$" + this.saldo);
        System.out.println("---------------------------");
    }
}
