package projetofinal;

import javax.swing.*;
import java.awt.*;

public class JanelaBoasVindas extends JFrame {

    public JanelaBoasVindas() {
        setTitle("Pokémon - UFPel");
        setSize(450, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel labelTitulo = new JLabel("Selecione uma opção para começar", SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(labelTitulo, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new GridLayout(3, 1, 0, 10));
        JButton btnCarregarJogo = new JButton("Carregar Jogo Salvo");
        JButton btnDefinirPosicao = new JButton("Posicionar Time Pokémon");
        JButton btnPosicaoAleatoria = new JButton("Distribuição Aleatória");

        painelBotoes.add(btnCarregarJogo);
        painelBotoes.add(btnDefinirPosicao);
        painelBotoes.add(btnPosicaoAleatoria);
        add(painelBotoes, BorderLayout.CENTER);

        btnCarregarJogo.addActionListener(e -> {
            GerenciadorDeArquivos gerenciador = new GerenciadorDeArquivos();
            Jogo jogoCarregado = gerenciador.carregarJogo(this);
            if (jogoCarregado != null) {
                new JanelaJogo(jogoCarregado).setVisible(true);
                dispose();
            }
        });

        // AGORA FUNCIONAL
        btnDefinirPosicao.addActionListener(e -> {
            new JanelaPosicionamento().setVisible(true);
            dispose();
        });

        btnPosicaoAleatoria.addActionListener(e -> {
            Jogo novoJogo = new Jogo();
            new JanelaJogo(novoJogo).setVisible(true);
            dispose();
        });
    }
}