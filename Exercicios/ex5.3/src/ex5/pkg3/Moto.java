package ex5.pkg3;

public class Moto extends Veiculo {
    //Atributos
    private int cilindradas;

    //Construtores (é obrigatório chamar o construtor do pai se ele foi definido)
    public Moto(String marca, String modelo, int ano, int cilindradas){
        super(marca, modelo, ano);
        this.cilindradas = cilindradas;
    }

    //Métodos
    @Override
    protected double calcularCustoManutencao() {
        return super.calcularCustoManutencao() + 100;
    }
}
