package ex5.pkg3;

public class Main {
    public static void main(String[] args) {
        Carro c1 = new Carro("Honda", "Civic", 2004, 4);
        Moto m1 = new Moto("Honda", "Sei la", 2012, 300);
        System.out.println("Custo de manutenção do carro: " + c1.calcularCustoManutencao());
        System.out.println("Custos de manutenção da moto: " + m1.calcularCustoManutencao());
    }
    
}
