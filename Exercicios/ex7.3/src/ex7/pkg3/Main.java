package ex7.pkg3;

public class Main {
    public static void main(String[] args) {
        Peixe peixe = new Peixe("Nemo");
        Pato pato = new Pato("Heitor");
        Passaro passaro = new Passaro("Juninho");

        peixe.mover();
        pato.mover();
        passaro.mover();
    }
    
}
