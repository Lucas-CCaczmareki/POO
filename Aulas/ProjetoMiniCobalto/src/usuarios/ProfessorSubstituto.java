/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios;

/**
 *
 * @author felipe
 */
public class ProfessorSubstituto extends Professor {
    private int tempoDeContrato;
    
    public ProfessorSubstituto(String cpf, String nome, int tempoDeContrato) {
        super(cpf, nome);
        this.tempoDeContrato = tempoDeContrato;
    }
    
    public void imprimir() {
        System.out.println("-- Professor Substituto ----------------------------");
        super.imprimir();
        System.out.println("Vigência do Contrato: " + tempoDeContrato + " meses");
    }

}
