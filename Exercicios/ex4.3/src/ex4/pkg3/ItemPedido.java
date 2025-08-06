package ex4.pkg3;

public class ItemPedido {
    //Atributos
    private int quantidade;
    private Produto produto; //isso aqui é a referência pro objeto produto que vai ser passado.

    public ItemPedido(int quantidade, Produto produto){
        this.quantidade = quantidade;
        this.produto = produto;
    }


    //Getters e setters
    public Produto getProduto() {
        return produto;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
