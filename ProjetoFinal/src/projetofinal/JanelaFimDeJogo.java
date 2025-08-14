package projetofinal;

import javax.swing.*;

/**
 * Janela exibida quando o jogo termina.
 * [cite_start]Informa o vencedor e oferece a opção de "Novo Jogo". [cite: 134, 135]
 */
public class JanelaFimDeJogo extends JFrame {
    private JLabel labelVencedor;
    private JButton botaoNovoJogo;

    public JanelaFimDeJogo(String nomeVencedor) {
        setTitle("Fim de Jogo!");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Adiciona e configura os componentes, como o label com o nome do vencedor.
    }
}