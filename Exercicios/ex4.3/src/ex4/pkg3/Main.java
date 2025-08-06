package ex4.pkg3;

/**
 *
 * @author lucas
 */
public class Main {
    public static void main(String[] args) {
        Produto cafe = new Produto("Café", 50.99);
        Produto filtro = new Produto("Filtro Melitta", 15.49);
        ItemPedido item1 = new ItemPedido(50, cafe);
        ItemPedido item2 = new ItemPedido(30, filtro);

        Pedido pedido = new Pedido(0);
        pedido.adicionarItem(item1);
        pedido.adicionarItem(item2);

        pedido.calcularTotalPedido();


    }
    
}
