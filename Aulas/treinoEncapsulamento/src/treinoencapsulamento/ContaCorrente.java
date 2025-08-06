/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package treinoencapsulamento;

/**
 *
 * @author lucas
 * Essa conta é uma subclasse de Conta.java
 * Através de herança, essa classe herda os atributos e métodos
 * da classe abstrata "Conta".
 */
//"extends" é o que estabelece a herança
//"implements" é o que assina o contrato da interface
public class ContaCorrente extends Conta implements Tributavel { 
    //Atributos
    private double limite = 200.00; //Um atributo que é específico da classe conta corrente

    //O construtor da filha DEVE chamar o construtor da mãe via super(). REGRA para evitar erros
    public ContaCorrente(String proprietario, int codigo, Banco banco) {
        super(proprietario, codigo, banco); //Chama o construtor da conta
    }

    //POLIMORFISMO: Sobreescrevendo o método sacar
    //Usa-se @Override para indicar que estamos redefinindo um método da classe mãe
    @Override
    public void sacar(double valor) {
        if( valor > 0 && (this.saldo + this.limite) >= valor ) {
            this.saldo -= valor;
            System.out.println("[Conta corrente] Saque de R$" + valor + " realizado com sucesso");
        } else {
            System.out.println("[Conta corrente] Saque não realizado. Limite insuficiente.");
        }
    }

    // Implementação do método abrastrato
    // A classe é OBRIGADA a implementar esse método.
    @Override
    public void imprimirExtrato() {
        System.out.println("--- Extrato conta corrente ---");
        System.out.println("Proprietário: " + this.proprietario);
        System.out.println("Banco: " + this.banco.getNome());
        System.out.println("Saldo: R$" + this.saldo);
        System.out.println("Limite Disponível: R$" + this.limite);
        System.out.println("----------------------------");
    }

    //Implementação do método da interface
    //Como os métodos de uma interface são todos abstract, é obrigatório a implementação dele aqui
    @Override
    public double calcularTributo() {
        //Tributo de 1% sobre o saldo
        return this.saldo * 0.01;
    }
}
