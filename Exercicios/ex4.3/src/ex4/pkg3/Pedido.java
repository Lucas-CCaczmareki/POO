package ex4.pkg3;

import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class Pedido {
    //Atributos
    private int idPedido;
    private ArrayList<ItemPedido> itensPedidos;

    //Construtor
    public Pedido(int idPedido){
        this.idPedido = idPedido;
        this.itensPedidos = new ArrayList<ItemPedido>(); //inicializa como um array vazio, que posteriormente vai receber os pedidos
    } 

    //Métodos
    public void adicionarItem(ItemPedido item) {
        this.itensPedidos.add(item);    //graças ao bom deus o .add sempre sabe onde colocar
    }

    public double calcularTotalPedido() {
        double total = 0;
        for(int i = 0; i < this.itensPedidos.size(); i++) {
            total += this.itensPedidos.get(i).getQuantidade() * this.itensPedidos.get(i).getProduto().getPreco();
        }
        System.out.println("Total do pedido é: R$" + total);
        return total;
    }
}
