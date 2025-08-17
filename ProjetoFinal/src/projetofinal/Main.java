package projetofinal;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Garante que a interface gráfica seja executada na thread de eventos (prática recomendada)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VisualizadorTabuleiro().setVisible(true);
            }
        });
    }
}
