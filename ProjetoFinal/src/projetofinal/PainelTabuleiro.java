package projetofinal;

import javax.swing.*;
import java.awt.*;

public class PainelTabuleiro extends JPanel {
    private Jogo jogo;
    
    //Construtor
    public PainelTabuleiro(Jogo jogo) {
        this.jogo = jogo;

    }

    public void montarTabuleiro() {
        //Limpa o que ta no painel
        this.removeAll();

        Tabuleiro tabuleiro = jogo.getTabuleiro();
        int tamanho = tabuleiro.getTamanhoN();

        setLayout(new GridLayout(tamanho, tamanho));

        for(int i = 0; i < tamanho; i++) {
            for(int j = 0; j < tamanho; j++) {
                final int linha = i;
                final int coluna = j;

                JButton botaoCelula = new JButton();

                 // --- CÓDIGO PARA PINTAR O FUNDO ---
                // 1. Pega o nome da região da célula atual
                String regiao = tabuleiro.getRegiaoDaCelula(i, j);

                // 2. Define a cor com base na região
                switch (regiao) {
                    case "Agua":
                        botaoCelula.setBackground(new Color(173, 216, 230)); // Azul claro
                        break;
                    case "Floresta":
                        botaoCelula.setBackground(new Color(144, 238, 144)); // Verde claro
                        break;
                    case "Terra":
                        botaoCelula.setBackground(new Color(210, 180, 140)); // Bege
                        break;
                    case "Eletricidade":
                        botaoCelula.setBackground(new Color(255, 255, 224)); // Amarelo claro
                        break;
                }
                // --- FIM DO CÓDIGO DE PINTURA ---

                // Adiciona o listener que avisa o Jogo sobre o clique
                botaoCelula.addActionListener(e -> {
                    jogo.jogadaDoJogador(linha, coluna);
                });

                add(botaoCelula);
            }
        }

        //Revalida e redeseja o painel pra mostrar os novos botöes?
        //(nao entendi direito)
        this.revalidate();
        this.repaint();

    }
}
