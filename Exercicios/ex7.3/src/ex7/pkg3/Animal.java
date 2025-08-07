package ex7.pkg3;

public abstract class Animal {
    //Atributos
    private String nome;
    
    //Construtor
    public Animal(String nome) {
        this.nome = nome;
    }

    //Métodos
    public abstract void mover();
    
    //Getters e setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
}
