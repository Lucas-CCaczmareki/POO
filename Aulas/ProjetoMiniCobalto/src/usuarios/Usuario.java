/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios;

/**
 *
 * @author felipe
 */
public class Usuario {
    private String cpf;
    private String nome;
    
    public Usuario(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    //opa gangam style
    
    public void imprimir() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
    }
}
