package ex4.pkg4;

/**
 *
 * @author lucas
 */
public class Disciplina {
    //Atributos
    private String nome;
    private String codigo;

    //Construtor
    public Disciplina(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    //Métodos getters e setters
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
}
