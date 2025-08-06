/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex3.pkg3;

/**
 *
 * @author lucas
 */
public class ItemPedido {
    //Atributos
    private String nome;
    private int quantidade;
    private double precoUnitario;

    //Construtores
    public ItemPedido(String nome, int quantidade, double precoUnitario) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public ItemPedido(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.precoUnitario = 0.0;
    }

    public ItemPedido(){
        this.nome = "Desconhecido";
        this.quantidade = 0;
        this.precoUnitario = 0.0;
    }

    //Métodos
    public double calcularTotal() {
        System.out.println("O produto " + this.nome + " tem custo total = " + (this.quantidade*this.precoUnitario));
        return this.quantidade * this.precoUnitario;
    }

}
