/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex6.pkg4;

public class Pagamentos {
    //Atributos
    private double valor;
    private String data;
    
    //Construtor
    public Pagamentos(double valor, String data) {
        this.valor = valor;
        this.data = data;
    }

    //métodos
    void realizarPagamento() {
        System.out.println("Pagamento realizado!");
    }

    //Getters e setters
    public String getData() {
        return data;
    }public double getValor() {
        return valor;
    }
    public void setData(String data) {
        this.data = data;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    
}
