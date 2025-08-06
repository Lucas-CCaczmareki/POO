package ex4.pkg3;

/**
 *
 * @author lucas
 */
public class Produto {
    //Atributos
    private String nome;
    private double preco;

    //Construtor
    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }
    public double getPreco() {
        return preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
}
