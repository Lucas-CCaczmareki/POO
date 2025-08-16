package projetofinal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JanelaFimDeJogo extends JFrame {

    public JanelaFimDeJogo(Treinador vencedor, Treinador perdedor) {
        setTitle("Fim da Partida!");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel labelVencedor = new JLabel("A equipe vencedora é " + vencedor.getNome() + "!", SwingConstants.CENTER);
        labelVencedor.setFont(new Font("Arial", Font.BOLD, 18));
        add(labelVencedor, BorderLayout.NORTH);

        JPanel painelPlacar = new JPanel(new GridLayout(2, 1));
        JLabel labelMensagemPlacar = new JLabel("Placar Final:", SwingConstants.CENTER);
        JLabel labelPlacar = new JLabel(vencedor.getNome() + ": " + vencedor.getPontuacaoDoTime() + " | " + perdedor.getNome() + ": " + perdedor.getPontuacaoDoTime(), SwingConstants.CENTER);
        painelPlacar.add(labelMensagemPlacar);
        painelPlacar.add(labelPlacar);
        add(painelPlacar, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        JButton botaoNovoJogo = new JButton("Novo Jogo");
        JButton botaoFechar = new JButton("Fechar");
        painelBotoes.add(botaoNovoJogo);
        painelBotoes.add(botaoFechar);
        add(painelBotoes, BorderLayout.SOUTH);

        botaoNovoJogo.addActionListener(e -> {
            dispose();
            new JanelaBoasVindas().setVisible(true);
        });
        botaoFechar.addActionListener(e -> System.exit(0));
    }
}