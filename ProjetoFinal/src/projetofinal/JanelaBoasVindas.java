package projetofinal;

import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

/**
 * ATIVIDADE 1: Janela de boas-vindas.
 * Esta versão foi corrigida para atuar apenas como um menu, delegando a
 * lógica de criação e carregamento do jogo para as classes corretas.
 */
public class JanelaBoasVindas extends JFrame {

    public JanelaBoasVindas() {
        // --- Configurações da Janela ---
        setTitle("Pokémon - UFPel");
        setSize(450, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // --- Título ---
        JLabel labelTitulo = new JLabel("Selecione uma opção para começar", SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(labelTitulo, BorderLayout.NORTH);

        // --- Painel de Botões ---
        JPanel painelBotoes = new JPanel(new GridLayout(3, 1, 0, 10));
        JButton btnCarregarJogo = new JButton("Carregar Jogo Salvo");
        JButton btnDefinirPosicao = new JButton("Posicionar Time Pokémon");
        JButton btnPosicaoAleatoria = new JButton("Distribuição Aleatória");

        painelBotoes.add(btnCarregarJogo);
        painelBotoes.add(btnDefinirPosicao);
        painelBotoes.add(btnPosicaoAleatoria);
        add(painelBotoes, BorderLayout.CENTER);

        // --- Ações dos Botões ---

        // Ação para "Carregar Jogo Salvo" - AGORA FUNCIONAL
        btnCarregarJogo.addActionListener(e -> {
            GerenciadorDeArquivos gerenciador = new GerenciadorDeArquivos();
            Jogo jogoCarregado = gerenciador.carregarJogo(this);

            if (jogoCarregado != null) {
                // Se o jogo foi carregado com sucesso, inicia a janela do jogo com ele
                new JanelaJogo(jogoCarregado).setVisible(true);
                dispose(); // Fecha a janela atual
            }
        });

        // Ação para "Definir Posição" - Placeholder
        // Implementar esta tela é um trabalho complexo, mantido como placeholder.
        btnDefinirPosicao.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Funcionalidade 'Posicionar Time' a ser implementada.");
        });

        // Ação para "Posição Aleatória" - AGORA SIMPLIFICADO
        btnPosicaoAleatoria.addActionListener(e -> {
            // 1. Cria a instância da lógica do jogo. O construtor de Jogo fará todo o trabalho.
            Jogo novoJogo = new Jogo();
            // 2. Cria a janela do jogo, passando a lógica para ela.
            new JanelaJogo(novoJogo).setVisible(true);
            // 3. Fecha a janela atual.
            dispose();
        });
    }
}