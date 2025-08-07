package ex7.pkg1;

public class Main {
    public static void main(String[] args) {
        Atendente Nuono = new Atendente("Nuono", "000", 5000);
        Gerente G1 = new Gerente("Cara fodase", "001", 8000, 200);
        Diretor D1 = new Diretor("Pai da LuoLi", "999", 10000, 100);

        System.out.println("Atendente: " + Nuono.calcularBonificacao());
        System.out.println("Gerente: " + G1.calcularBonificacao());
        System.out.println("Diretor: " + D1.calcularBonificacao());
    }
}
