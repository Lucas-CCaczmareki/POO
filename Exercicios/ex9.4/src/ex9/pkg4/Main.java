/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ex9.pkg4;

/**
 *
 * @author lucas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Aluno a1 = new Aluno("Lucas", "241035", 10.0);
            System.out.println("Nota inserida com sucesso");
        } catch (NotaInvalidaException e) {
            System.out.println(e);
        } catch (NomeInvalidoException e) {
            System.out.println(e);
        } catch (MatriculaInvalidaException e) {
            System.out.println(e);
        }
        finally {
            System.out.println("Encerrando programa...");
        }
        
    }
    
}
