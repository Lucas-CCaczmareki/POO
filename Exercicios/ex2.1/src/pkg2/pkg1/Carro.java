package pkg2.pkg1;

public class Carro {
    //Atributos
    private String marca;
    private String modelo;
    private int ano;

    //Construtor
    public Carro(String marca, String modelo, int ano){
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    //Métodos
    public void getInfo(){
        System.out.println("-----------------------------------");
        System.out.println("Marca: " + this.marca);
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Ano: " + this.ano);
        System.out.println("-----------------------------------");
    }

    //Setters
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }

    
}
