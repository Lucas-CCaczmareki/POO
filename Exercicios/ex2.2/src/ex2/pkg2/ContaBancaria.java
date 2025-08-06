package ex2.pkg2;

public class ContaBancaria {
    //Atributos
    private String numeroConta;
    private String titular;
    private double saldo;

    //Métodos
    public void depositar(double valor) {
        this.saldo += valor;
        System.out.println("Deposito de R$" + valor + " realizado com sucesso!");
    }

    public void sacar(double valor) {
        if(this.saldo >= valor) {
            this.saldo -= valor;
            System.out.println("Saque de R$" + valor + " realizado com sucesso!");
        } else {
            System.out.println("Tentativa de saque: R$" + valor + ". Saldo insuficiente. Impossível realizar saque!");
        }
    }

    //Getters
    public String getNumeroConta() {
        return numeroConta;
    }
    public double getSaldo() {
        return saldo;
    }
    public String getTitular() {
        return titular;
    }

    //Setter
    public void setTitular(String titular) {
        this.titular = titular;
    }
}
