package projetofinal;

import javax.swing.*;
import java.awt.*;

public class PainelTabuleiro extends JPanel {
    //Construtor
    public PainelTabuleiro(Jogo jogo) {
        int tamanho = jogo.getTabuleiro().getTamanhoN();
        setLayout(new GridLayout(tamanho, tamanho));

        for(int i = 0; i < tamanho; i++) {
            for(int j = 0; j < tamanho; j++) {
                final int linha = i;
                final int coluna = j;

                JButton botaoCelula = new JButton();
                // Adiciona o listener que avisa o Jogo sobre o clique
                botaoCelula.addActionListener(e -> {
                    jogo.jogadaDoJogador(linha, coluna);
                });

                add(botaoCelula);

            }
        }
    }
}
