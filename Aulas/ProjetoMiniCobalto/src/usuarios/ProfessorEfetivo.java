/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios;

/**
 *
 * @author felipe
 */
public class ProfessorEfetivo extends Professor {
    private String siape;
    
    public ProfessorEfetivo(String cpf, String nome, String siape) {
        super(cpf, nome);
        this.siape = siape;
    }
    
    public void imprimir() {
        System.out.println("-- Professor Efetivo -------------------------------");
        super.imprimir();
        System.out.println("SIAPE: " + siape);
    }

}
