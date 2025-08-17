package projetofinal;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // 1. Cria a instância da lógica do jogo.
        Jogo jogo = new Jogo();

        // 2. Agenda a criação da interface gráfica na Thread de Eventos do Swing (EDT).
        SwingUtilities.invokeLater(() -> {
            VisualizadorTabuleiro janela = new VisualizadorTabuleiro(jogo);
            janela.setVisible(true);
        });
    }
}
