package ex7.pkg2;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<AcoesVeiculo> garagem = new ArrayList<>();

        Carro civic = new Carro();
        Carro cruze = new Carro();
        Moto motoca = new Moto();
        
        garagem.add(civic);
        garagem.add(cruze);
        garagem.add(motoca);

        for(int i = 0; i < garagem.size(); i++) {
            garagem.get(i).ligar();
            garagem.get(i).desligar();
        }
    }
    
}
