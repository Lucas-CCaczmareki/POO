package ex5.pkg3;

public class Veiculo {
    //Atributos
    private String marca;
    private String modelo;
    private int ano;
    
    //Construtor
    public Veiculo(String marca, String modelo, int ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    //Métodos
    protected double calcularCustoManutencao() {
        return 500.00;
    }
}
