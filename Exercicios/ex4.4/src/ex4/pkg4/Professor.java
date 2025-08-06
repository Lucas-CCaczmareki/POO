package ex4.pkg4;

/**
 *
 * @author lucas
 */
public class Professor {
    //Atributos
    private String nome;
    private String matricula;
    private String departamento;

    //Construtore(s) ou seja, tem sobrecarga
    public Professor(){
        this.nome = "Fulaninho";
        this.matricula = "000000";
        this.departamento = "Desconhecido";
    }

    public Professor(String nome, String matricula, String departamento) {
        this.nome = nome;
        this.matricula = matricula;
        this.departamento = departamento;
    }

    //Getters e setters
    public String getDepartamento() {
        return departamento;
    }
    public String getMatricula() {
        return matricula;
    }
    public String getNome() {
        return nome;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
