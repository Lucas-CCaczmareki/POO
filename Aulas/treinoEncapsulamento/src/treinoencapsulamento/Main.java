/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package treinoencapsulamento;

/**
 *
 * @author lucas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Instancia um banco
        Banco meuBanco = new Banco("Nubank");

        //Demonstrando a composição
        //Criando contas de tipos diferentes A PARTIR desse banco (banco é composto por contas) ou (contas faz parte de banco)
        ContaCorrente cc = meuBanco.criarContaCorrente("Lucas", 101);
        ContaPoupanca cp = meuBanco.criarContaPoupanca("Ana", 202);

        cc.depositar(1000);
        cp.depositar(500);

        //Demonstrando o polimorfismo
        meuBanco.processarContas();

        System.out.println("\n--- Testando a Interface Tributavel ---");
        System.out.println("Tributo da Conta Corrente: R$" + cc.calcularTributo());
        System.out.println("");

        // Demonstrando o método específico da Conta Poupança
        cp.renderJuros(0.05); //rendimento de 5% sobre o montante
        cp.imprimirExtrato();
        
        /* 
        //1. Criar os bancos
        Banco bb = new Banco("Banco do Brasil", 1);
        Banco nubank = new Banco("Nu pagamentos SA", 8);

        // 2. Usar os objetos banco para criar as contas
        //AQUI OCORRE A ASSOCIAÇÃO!
        Conta lucasAcc  = nubank.criarConta("Lucas C. Caczmareki", 1234);
        Conta anaAcc = bb.criarConta("Ana Luiza Santos", 5678);

        System.out.println("\n------- Operações na conta do Lucas -------");
        //Usando o getter da conta pra pegar o nome do banco associado!
        System.out.println("Banco da conta: " + lucasAcc.getNomeDoBanco());
        System.out.println("Saldo inicial: " + lucasAcc.getSaldo());
        lucasAcc.depositar(500.0);
        lucasAcc.sacar(100.0);
        System.out.println("Saldo final: " + lucasAcc.getSaldo());

        System.out.println("\n------- Operações na conta da Ana -------");
        System.out.println("Banco da conta: " + anaAcc.getNomeDoBanco());
        System.out.println("Saldo inicial: " + lucasAcc.getSaldo());
        anaAcc.depositar(1000.0);
        anaAcc.sacar(1200.0);
        System.out.println("Saldo final: " + anaAcc.getSaldo());

        System.out.println("\n------- Buscando conta no Banco do Brasil -------");
        Conta contaEncontrada = bb.buscarConta(5678);
        System.out.println("Proprietario da conta 5678: " + contaEncontrada.getProprietario());
        */
    }
    
}
