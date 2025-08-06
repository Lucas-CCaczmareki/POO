package ex1.pkg3;

public class Main {
    public static void main(String[] args) {
        Produto P1 = new Produto("Arroz", 8.99, 10);
        

        P1.reporEstoque(10);
        System.out.println(P1.getQuantidadeEmEstoque());

        P1.vender(30);
        P1.vender(10);
        P1.getInfo();

    }
    
}
