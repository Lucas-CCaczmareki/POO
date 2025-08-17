package projetofinal;

//import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualizadorTabuleiro extends JFrame {
    //Atributos
    private Jogo jogo;

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
                botaoCelula.addActionListener(e -> {
                    jogo.jogadaDoJogador(linha, coluna);
                });

                painelTabuleiro.add(botaoCelula);
            }
        }
        //não entendi o que esse add aqui faz
        add(painelTabuleiro);

    }
    
    /*
    private Tabuleiro tabuleiro;
    private static final int TAMANHO_TABULEIRO = 8;

    public VisualizadorTabuleiro() {
        // 1. Inicializa o tabuleiro e posiciona os Pokémon
        tabuleiro = new Tabuleiro();
        posicionarPokemonsDeExemplo();

        // 2. Configurações da Janela Principal (JFrame)
        setTitle("Visualizador de Tabuleiro Pokémon");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 3. Cria o painel que conterá a grade de botões
        JPanel painelTabuleiro = new JPanel();
        painelTabuleiro.setLayout(new GridLayout(TAMANHO_TABULEIRO, TAMANHO_TABULEIRO));

        // 4. Preenche o painel com botões, um para cada célula
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                Celula celula = tabuleiro.getCelula(i, j);
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

                // Se houver um Pokémon na célula, mostra o nome dele no botão
                if (celula.getPkm() != null) {
                    botaoCelula.setText(celula.getPkm().getNome());
                    botaoCelula.setFont(new Font("Arial", Font.BOLD, 10));
                }
                
                // Adiciona um ouvinte de eventos para saber quando o botão é clicado
                botaoCelula.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String info = "Célula: [" + celula.getLinha() + "][" + celula.getColuna() + "]";
                        info += " | Região: " + tabuleiro.getRegiaoDaCelula(celula.getLinha(), celula.getColuna());
                        if (celula.getPkm() != null) {
                            info += " | Contém: " + celula.getPkm().getNome();
                        } else {
                            info += " | Contém: Nada";
                        }
                        System.out.println(info);
                    }
                });

                painelTabuleiro.add(botaoCelula);
            }
        }
        
        // Adiciona o painel com a grade à janela
        add(painelTabuleiro);
    }

    private void posicionarPokemonsDeExemplo() {
        // Região Água (0-3, 0-3)
        tabuleiro.posicionarPokemon(new PokemonAgua("Squirtle"), 1, 2);
        
        // Região Floresta (0-3, 4-7)
        tabuleiro.posicionarPokemon(new PokemonFloresta("Bulbasaur"), 0, 5);

        // Região Terra (4-7, 0-3)
        tabuleiro.posicionarPokemon(new PokemonTerra("Sandshrew"), 6, 1);

        // Região Eletricidade (4-7, 4-7)
        tabuleiro.posicionarPokemon(new PokemonEletrico("Pikachu"), 5, 5);
    }

    public static void main(String[] args) {
        // Garante que a interface gráfica seja executada na thread de eventos (prática recomendada)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VisualizadorTabuleiro().setVisible(true);
            }
        });
    }
    */
}
