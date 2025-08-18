package projetofinal;

import javax.swing.*;
import java.awt.*;

/*
 * Classe responsável por criar a janela (extende JFrame) de boas vindas, com as 3 opções
 * - Carregar jogo salvo
 * - Posicionar pokémon
 * - Distribuição aleatória
 */

public class JanelaBoasVindas extends JFrame {

    //Construtor
    public JanelaBoasVindas() {

        setTitle("Pokémon - UFPel");                //nome da janela
        setSize(450, 250);                   //tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //fecha quando clica no x
        setLocationRelativeTo(null);                    //centraliza ela na tela
        setLayout(new BorderLayout(10, 10));    //define o layout da borda

        //define uma borda interna. Puramente estético
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        //Adiciona um título na janela
        JLabel labelTitulo = new JLabel("Selecione uma opção para começar", SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(labelTitulo, BorderLayout.NORTH);

        //Cria uma área e coloca os botões lá
        JPanel painelBotoes = new JPanel(new GridLayout(3, 1, 0, 10));
        JButton btnCarregarJogo = new JButton("Carregar Jogo Salvo");
        JButton btnDefinirPosicao = new JButton("Posicionar Time Pokémon");
        JButton btnPosicaoAleatoria = new JButton("Distribuição Aleatória");

        //adiciona os botões no painel, de forma centralizada
        painelBotoes.add(btnCarregarJogo);
        painelBotoes.add(btnDefinirPosicao);
        painelBotoes.add(btnPosicaoAleatoria);
        add(painelBotoes, BorderLayout.CENTER);

        //Quando clicado, busca pelo arquivo de save e cria a janela do jogo conforme o arquivo
        btnCarregarJogo.addActionListener(e -> {
            GerenciadorDeArquivos gerenciador = new GerenciadorDeArquivos();
            Jogo jogoCarregado = gerenciador.carregarJogo(this);
            if (jogoCarregado != null) {
                new JanelaJogo(jogoCarregado).setVisible(true);
                dispose(); //fecha e libera os recursos da janela atual
            }
        });

        //Quando clicado, abre a janela para o jogador escolher o pokémon e posicioná-lo
        btnDefinirPosicao.addActionListener(e -> {
            new JanelaPosicionamento().setVisible(true);
            dispose(); //fecha a janela de boas vindas
        });

        //Quando clicado inicia um novo jogo (em distribuição aleatória)
        btnPosicaoAleatoria.addActionListener(e -> {
            Jogo novoJogo = new Jogo();
            new JanelaJogo(novoJogo).setVisible(true);
            dispose(); //fecha a janela de boas vindas
        });
    }
}