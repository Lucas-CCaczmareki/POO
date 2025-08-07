package ex8.pkg3;

public class Pessoa implements Comparable<Pessoa> {
    //Atributos
    private String nome;
    private int idade;

    //Construtor
    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    //Métodos
    @Override
    public int compareTo(Pessoa p) {
        //Retorna -1 se precisa estar antes.
        //Retorna 0 se são "iguais" em ordem.
        //Retorna 1 se precisa estar depois.
        
        if(this.idade < p.getIdade()) {
            return -1;
        } else if(this.idade > p.getIdade()) {
            return 1;
        } else {
            return 0;
        }
    }

    //Getters e setters
    public int getIdade() {
        return idade;
    }
    public String getNome() {
        return nome;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
