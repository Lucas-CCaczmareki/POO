package ex1.pkg3;

public class Produto {
    //Atributos
    private String nome;
    private double preco;
    private int quantidadeEmEstoque;

    //Construtor

    public Produto(String nome, double preco, int quantidadeEmEstoque) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    //Métodos
    public boolean vender(int quantidade) {
        if(quantidadeEmEstoque > quantidade) {
            quantidadeEmEstoque -= quantidade;
            System.out.println("Venda de " + quantidade + " " + this.nome + " realizada!");
            return true;
        } else {
            System.out.println("Impossível realizar venda. Estoque insuficiente!");
            return false;
        }
    }

    public void reporEstoque(int quantidade) {
        quantidadeEmEstoque += quantidade;
    }

    //Setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    //Getters
    public String getNome() {
        return nome;
    }
    public double getPreco() {
        return preco;
    }
    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void getInfo() {
        System.out.println("---------------------------------");
        System.out.println("Produto: " + this.nome);
        System.out.println("Preço: " + this.preco);
        System.out.println("Quantidade em estoque: " + this.quantidadeEmEstoque);
        System.out.println("---------------------------------");
    }
}
