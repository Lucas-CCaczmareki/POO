package projetofinal;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        // Tenta aplicar o Look and Feel do sistema operacional para uma aparência mais nativa.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Garante que a interface gráfica seja iniciada na thread correta (EDT).
        SwingUtilities.invokeLater(() -> {
            // Usa o nome corrigido da nossa classe de boas-vindas.
            new JanelaBoasVindas().setVisible(true);
        });
    }
}