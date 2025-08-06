package ex5.pkg3;

public class Carro extends Veiculo{
    //Atributos
    private int numPortas;

    public Carro(String marca, String modelo, int ano, int numPortas) {
        super(marca, modelo, ano);
        this.numPortas = numPortas;
    }
    
    //Métodos
    @Override
    protected double calcularCustoManutencao() {
        return super.calcularCustoManutencao() + 200;
    }
}
