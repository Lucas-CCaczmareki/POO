package projetofinal;

import javax.swing.*;

/**
 * Janela principal onde o jogo acontece.
 * [cite_start]Exibe a grade/tabela de botões e as opções de jogo. [cite: 110]
 */
public class JanelaJogo extends JFrame {
    // Componentes da janela
    private JPanel painelTabuleiro;
    private JButton botaoTrocarPokemon;
    private JButton botaoDica;
    private JButton botaoDebug;
    private JButton botaoSair;
    private JLabel labelPontuacaoJogador;
    private JLabel labelPontuacaoComputador;

    public JanelaJogo() {
        setTitle("Pokémon - Batalha");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Configura o layout e adiciona os componentes.
    }
}
