package ex2.pkg2;

public class Main {
    public static void main(String[] args) {
        ContaBancaria myAccount = new ContaBancaria();
        myAccount.setTitular("Lucas Cavallin Caczmareki");
        myAccount.depositar(500);
        myAccount.sacar(1000);
        myAccount.sacar(100);

        System.out.println("Saldo disponível: R$" + myAccount.getSaldo());
    
    }

    
}
