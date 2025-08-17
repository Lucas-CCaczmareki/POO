package projetofinal;

//import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualizadorTabuleiro extends JFrame {
    //Atributos
    private Jogo jogo;

    private JPanel painelBatalha;
    private JTextArea logBatalha;
    private JButton botaoAtacar;
    private JButton botaoFugir;

    public VisualizadorTabuleiro(Jogo jogo) {
        this.jogo = jogo;

        setTitle("Pokémon - Jogo de tabuleiro");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        criarComponentes();
    }

    private void criarComponentes() {
        Tabuleiro tabuleiro = jogo.getTabuleiro();
        int tamanho = tabuleiro.getTamanhoN();

        JPanel painelTabuleiro = new JPanel(new GridLayout(tamanho, tamanho));

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                //Cria uma versão congelada da variável que vai ser enviada pro botão
                //Ai essa variável não vai mudar, e o botão vai continuar referenciando a célula certa
                final int linha = i;
                final int coluna = j;

                JButton botaoCelula = new JButton();

                // Define a cor de fundo baseada na região
                String regiao = tabuleiro.getRegiaoDaCelula(i, j);
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

                //Ai aqui adiciona os action listener no botão que envia a linha e coluna correspondente pra jogada
                // e -> aqui funciona como uma espécie de "atalho", para não ter que escrever o new ActionListener...
                    //com public void actionPerformed, etc.
                    
                botaoCelula.addActionListener(e -> {
                    jogo.jogadaDoJogador(linha, coluna);
                });

                painelTabuleiro.add(botaoCelula);
            }
        }
        //não entendi o que esse add aqui faz
        add(painelTabuleiro);

    }
}
