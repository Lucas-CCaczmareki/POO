/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p2_ex4;

//importa todas as classes definidas no pacote.
//import exercicio03.*; 


/**
 *
 * @author lucas
 */
public class contador {
    //Atributos
    private int valor;
    private int limite;
    
    //Método construtuor
    public contador(int limite) {
        //this.limite = limite;
        //this.valor = 0;
        this(0, limite);
    }
    
    public contador(int valor, int limite) {
        this.limite = limite;
        AlterarValor(valor);
    }
    
    //Métodos
    public void Incremento() {
        valor++;
        if (valor == limite) {
            valor = 0;
        }
    }
    
    public int DevolveValor() {
        return valor;
    }
    
    public String DevolveString() {
        //Operador ternário (if then, else)
        //Se a condição for verdadeira, executa oq ta antes dos :, se for falsa, executa o que ta depois
        return( valor < 10 ) ? "0" + valor: "" + valor;
        //Esse retorno garante que nosso relógio tenha o formato 00:00 através da concatenação
    }
    
    public void AlterarValor(int valor) {
        if( valor >= 0 && valor < limite ) {
            this.valor = valor;
        }
    }
}
