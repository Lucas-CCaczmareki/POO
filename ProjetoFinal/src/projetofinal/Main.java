package projetofinal;

import javax.swing.SwingUtilities;  //Biblioteca da interface gráfica
import javax.swing.UIManager;       //Biblioteca de visual para a interface

public class Main {
    public static void main(String[] args) {
        
        try {
            //Pega o visual de janelas, etc do teu sistema e aplica no swing
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            //Se der errado, demonstra isso, mas não para o programa
            //Ai a interface fica com o visual padrão do Java.
            e.printStackTrace();
        }
        
        /*
         * Garante que a criação da interface gráfica ocorre na thread correta.
         */
        SwingUtilities.invokeLater(() -> {
            //Cria uma janela boas vindas e abre ela
            new JanelaBoasVindas().setVisible(true);
        });
    }
}