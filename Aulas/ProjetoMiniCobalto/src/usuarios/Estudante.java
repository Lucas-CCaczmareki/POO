/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios;

/**
 *
 * @author felipe
 */
public class Estudante extends Usuario {
    private String numeroDeMatricula;
    
    //Importa o método construtor da super classe (obrigatório se a classe foi extendida)
    public Estudante(String cpf, String nome, String matricula) {
        super(cpf, nome);
        this.numeroDeMatricula = matricula;
    }
    
    @Override
    public void imprimir() {
        System.out.println("-- Estudante ---------------------------------------");
        super.imprimir();
        System.out.println("Número de Matrícula: " + numeroDeMatricula);
    }
}
