package projetofinal;

import javax.swing.*;

/**
 * Tela inicial do jogo.
 * Oferece as opções de "carregar jogo salvo", "posicionar seu time Pokémon"
 * [cite_start]ou "distribuir Pokémons de maneira aleatória". [cite: 96]
 */
public class JanelaBoasVindas extends JFrame {
    // Componentes da janela (botões, labels, etc.)
    private JButton botaoCarregar;
    private JButton botaoPosicionar;
    private JButton botaoAleatorio;

    public JanelaBoasVindas() {
        // Configurações da janela (título, tamanho, layout, etc.)
        setTitle("Bem-vindo ao Pokémon!");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela.
        // Adiciona os componentes à janela.
    }
}
