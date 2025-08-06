public class Carro {
    //Atributos
    String marca;
    String modelo;
    String ano;
    double velocidadeAtual;

    //Métodos
    boolean acelerar(double incremento){
        if(velocidadeAtual + incremento <= 220){
            velocidadeAtual += incremento;
            return true;
        } else {
            return false;
        }
    }

    boolean frear(double decremento){
        if(velocidadeAtual - decremento >= 0){
            velocidadeAtual -= decremento;
            return true;
        } else {
            return false;
        }
    }

    void mostrarVelocidade(){
        System.out.println("\nA velocidade atual é de: " + velocidadeAtual + "Km/h");
    }

    void mostraDados(){
        System.out.println("-------------------------------------");
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Ano: " + ano);
        System.out.println("-------------------------------------");
    }

    public static void main(String args[]){
        Carro trovaoAzul;
        trovaoAzul = new Carro();

        //------------ Inserindo Dados -----------------
        trovaoAzul.marca = "Honda";
        trovaoAzul.modelo = "Civic";
        trovaoAzul.ano = "2004";
        trovaoAzul.velocidadeAtual = 0;

        //------------- Testando métodos -----------------
        trovaoAzul.acelerar(220);
        trovaoAzul.mostrarVelocidade();
        trovaoAzul.frear(0.2);
        trovaoAzul.mostrarVelocidade();

        trovaoAzul.mostraDados();
    }
}
