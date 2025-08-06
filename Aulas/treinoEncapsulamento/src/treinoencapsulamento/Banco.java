/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package treinoencapsulamento;

/**
 *
 * @author lucas
 */
public class Banco {
    //Atributos
    private String nome; 
    private Conta[] contas;
    private int numContasCriadas;  

    //Métodos construtores
    public Banco(String nome) {
        this.nome = nome;
        //Instancia o vetor com tamanho máximo de 5 posições
        this.contas = new Conta[5]; //cria espaço pra 5 objetos da classe conta numa estrutura vetorial
        this.numContasCriadas = 0;  //inicializa o contador de contas criadas em 0
    }

    /* 
    //Métodos
    public Conta criarConta(String nomeProprietario, int codigoConta) {
        //Verifica se o número do contador é menor que o tamanho total do vetor
        if (this.numContasCriadas < this.contas.length) {
            Conta novaConta = new Conta(nomeProprietario, codigoConta, this);

            //Adiciona a nova conta criada na posição livre indicada pelo contador
            this.contas[this.numContasCriadas] = novaConta;

            // Incrementa o contador de contas criadas pra dar certo na próxima chamada
            this.numContasCriadas++;

            System.out.println("Conta para " + nomeProprietario + " criada no " + this.nome);
            return novaConta;
        } else {
            //Se o contador jpa tiver no tamanho máximo, o banco ta cheio, indica erro
            System.out.println("Erro: O " + this.nome + " atingiu o número máximo de contas.");
            return null;
        }
    }
    */

    //Método para criar uma Conta Corrente
    public ContaCorrente criarContaCorrente(String nomeProprietario, int codigoConta) {
        if( this.numContasCriadas < this.contas.length ) { // cria apenas se houver espaço no vetor
            ContaCorrente novaConta = new ContaCorrente(nomeProprietario, codigoConta, this);
            this.contas[this.numContasCriadas++] = novaConta;
            System.out.println("Conta corrente para " + nomeProprietario + " criada com sucesso!");
            return novaConta;
        }
        return null; //retorna nulo caso o vetor esteja cheio
    }

    // Método para criar Conta poupança
    public ContaPoupanca criarContaPoupanca(String nomeProprietario, int codigoConta) {
        if( this.numContasCriadas < this.contas.length ) {
            ContaPoupanca novaConta = new ContaPoupanca(nomeProprietario, codigoConta, this);
            this.contas[numContasCriadas++] = novaConta;
            System.out.println("Conta poupança para " + nomeProprietario + " criada com sucesso!");
            return novaConta;
        }
        return null;
    }

    //Método para demonstrar o polimorfismo
    public void processarContas() {
        System.out.println("\n>>> Processando todas as contas do banco: " + this.nome);
        
        for(int i = 0; i < numContasCriadas; i++) {
            Conta C = this.contas[i]; //C é do tipo abstrato

            //O java sabe qual método chamar, se for uma contacorrente, ele chama o método de lá, se for outro tipo, chama o desse outro tipo
            //Isso é o polimorfismo
            //C.sacar(100.0);
            C.imprimirExtrato();
        }
    }

    public Conta buscarConta(int codigoConta) {
        //Percorre o vetor até onde ele está preenchido com contas e retorna a conta se encontrar
        for (int i = 0; i < this.numContasCriadas; i++) {
            if (contas[i].getCodigo() == codigoConta) {
                return contas[i];
            }
        }
        return null;
    }

    //Getters
    public String getNome() {
        return this.nome;
    }
}
