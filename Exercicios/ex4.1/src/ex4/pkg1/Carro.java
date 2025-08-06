package ex4.pkg1;

/**
 *
 * @author lucas
 */
public class Carro {
    //Atributos
    private String marca;
    private String modelo;
    private Motor motor;

    //Métodos
    public void getInfo(){
        System.out.println("-------------------------");
        System.out.println("Marca:\t\t" + marca);
        System.out.println("Modelo:\t\t" + modelo);
        System.out.println("Motor\t\t-----\nTipo:\t\t" + motor.getTipo() + "\nPotencia:\t" + motor.getPotencia());
        System.out.println("-------------------------");
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    //Observe a agredação, o motor passado como atributo do carro CONTINUA a existir, mesmo que o carro deixe de existir
    public void setMotor(Motor motor) {
        this.motor = motor;
    }

}
