package projetofinal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class JanelaJogo extends JFrame implements ObservadorJogo {

    private final Jogo jogo;
    private final JButton[][] botoesGrid;
    private final JLabel labelPontuacaoJogador;
    private final JLabel labelPontuacaoComputador;
    private final JLabel labelStatus;
    private final JButton btnDica;
    private final int TAMANHO_GRID;

    public JanelaJogo(Jogo jogo) {
        this.jogo = jogo;
        this.jogo.adicionarObservador(this);
        this.TAMANHO_GRID = jogo.getTabuleiro().getTamanhoN();
        this.botoesGrid = new JButton[TAMANHO_GRID][TAMANHO_GRID];

        setTitle("Pokémon UFPel - Partida em Andamento");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        // --- Painel Superior (Scores e Status) ---
        JPanel painelSuperior = new JPanel(new BorderLayout());
        labelStatus = new JLabel("", SwingConstants.CENTER);
        labelStatus.setFont(new Font("Arial", Font.ITALIC, 14));
        JPanel painelScores = new JPanel(new GridLayout(1, 2, 20, 0));
        labelPontuacaoJogador = new JLabel();
        labelPontuacaoJogador.setFont(new Font("Arial", Font.BOLD, 16));
        labelPontuacaoComputador = new JLabel();
        labelPontuacaoComputador.setFont(new Font("Arial", Font.BOLD, 16));
        painelScores.add(labelPontuacaoJogador);
        painelScores.add(labelPontuacaoComputador);
        painelSuperior.add(painelScores, BorderLayout.NORTH);
        painelSuperior.add(labelStatus, BorderLayout.CENTER);
        add(painelSuperior, BorderLayout.NORTH);

        // --- Painel Central (Grid de Botões) ---
        JPanel painelGrid = new JPanel(new GridLayout(TAMANHO_GRID, TAMANHO_GRID, 5, 5));
        for (int i = 0; i < TAMANHO_GRID; i++) {
            for (int j = 0; j < TAMANHO_GRID; j++) {
                final int linha = i;
                final int coluna = j;
                JButton botaoCelula = new JButton("?");
                botaoCelula.setFont(new Font("Arial", Font.BOLD, 20));
                botaoCelula.addActionListener(e -> jogo.processarJogadaJogador(linha, coluna));
                botoesGrid[i][j] = botaoCelula;
                painelGrid.add(botaoCelula);
            }
        }
        add(painelGrid, BorderLayout.CENTER);

        // --- Painel Inferior (Botões de Opção) ---
        JPanel painelOpcoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        JButton btnTrocarPokemon = new JButton("Trocar Pokémon");
        btnDica = new JButton("Dica (" + jogo.getDicasRestantes() + ")");
        JButton btnDebug = new JButton("Debug");
        JButton btnSair = new JButton("Sair");

        btnTrocarPokemon.addActionListener(e -> {
            Treinador jogador = jogo.getJogador();
            List<Pokemon> mochila = jogador.getMochila();

            if (mochila.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Sua mochila está vazia!", "Mochila Vazia", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String[] opcoes = new String[mochila.size()];
            for (int i = 0; i < mochila.size(); i++) {
                opcoes[i] = mochila.get(i).getNome() + " (Nível: " + mochila.get(i).getNivel() + ")";
            }

            String escolha = (String) JOptionPane.showInputDialog(this, "Escolha um Pokémon da mochila:", "Trocar Pokémon Principal", JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

            if (escolha != null) {
                int indiceEscolhido = -1;
                for (int i = 0; i < opcoes.length; i++) {
                    if (opcoes[i].equals(escolha)) {
                        indiceEscolhido = i;
                        break;
                    }
                }
                if (indiceEscolhido != -1) {
                    jogador.trocarPokemonPrincipal(indiceEscolhido);
                    jogo.notificarObservadores("JOGADA_CONCLUIDA", null);
                }
            }
        });

        btnDica.addActionListener(e -> {
            if (jogo.getDicasRestantes() <= 0) {
                JOptionPane.showMessageDialog(this, "Você não tem mais dicas!", "Dica", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            try {
                String linhaStr = JOptionPane.showInputDialog(this, "Digite a linha para a dica (0-" + (TAMANHO_GRID - 1) + "):", "Dica - Linha", JOptionPane.QUESTION_MESSAGE);
                if (linhaStr == null) return;
                int linha = Integer.parseInt(linhaStr);

                String colunaStr = JOptionPane.showInputDialog(this, "Digite a coluna para a dica (0-" + (TAMANHO_GRID - 1) + "):", "Dica - Coluna", JOptionPane.QUESTION_MESSAGE);
                if (colunaStr == null) return;
                int coluna = Integer.parseInt(colunaStr);

                if (linha < 0 || linha >= TAMANHO_GRID || coluna < 0 || coluna >= TAMANHO_GRID) {
                    JOptionPane.showMessageDialog(this, "Posição inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                boolean dicaPositiva = jogo.usarDica(linha, coluna);
                if (dicaPositiva) {
                    JOptionPane.showMessageDialog(this, "Dica: Sim, existe pelo menos um Pokémon nessa linha ou coluna!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Dica: Não, não há nenhum Pokémon nessa linha ou coluna.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                }
                btnDica.setText("Dica (" + jogo.getDicasRestantes() + ")");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Entrada inválida. Digite um número.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnDebug.addActionListener(e -> jogo.ativarModoDebug());

        btnSair.addActionListener(e -> {
            Object[] options = {"Salvar e Sair", "Sair sem Salvar", "Cancelar"};
            int resposta = JOptionPane.showOptionDialog(this, "Deseja salvar o jogo antes de sair?", "Sair", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (resposta == JOptionPane.YES_OPTION) {
                new GerenciadorDeArquivos().salvarJogo(this, jogo);
                System.exit(0);
            } else if (resposta == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        });

        painelOpcoes.add(btnTrocarPokemon);
        painelOpcoes.add(btnDica);
        painelOpcoes.add(btnDebug);
        painelOpcoes.add(btnSair);
        add(painelOpcoes, BorderLayout.SOUTH);

        atualizar("INICIO", null);
    }

    @Override
    public void atualizar(String evento, Object dados) {
        SwingUtilities.invokeLater(() -> {
            atualizarVisualizacaoGeral();

            switch (evento) {
                case "POKEMON_CAPTURADO":
                    Pokemon pCapturado = (Pokemon) dados;
                    JOptionPane.showMessageDialog(this, "Parabéns! Você capturou um " + pCapturado.getNome() + "!", "Captura Realizada!", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "CAPTURA_FALHOU":
                    Pokemon pFugitivo = (Pokemon) dados;
                    JOptionPane.showMessageDialog(this, "Ah, não! O " + pFugitivo.getNome() + " escapou da Pokébola!", "Captura Falhou", JOptionPane.WARNING_MESSAGE);
                    break;
            }

            if (jogo.isJogoTerminou()) {
                Treinador vencedor = jogo.getJogador().getPontuacaoDoTime() >= jogo.getComputador().getPontuacaoDoTime() ? jogo.getJogador() : jogo.getComputador();
                Treinador perdedor = vencedor == jogo.getJogador() ? jogo.getComputador() : jogo.getJogador();
                new JanelaFimDeJogo(vencedor, perdedor).setVisible(true);
                dispose();
            }
        });
    }

    private void atualizarVisualizacaoGeral() {
        labelPontuacaoJogador.setText("Sua Pontuação: " + jogo.getJogador().getPontuacaoDoTime());
        labelPontuacaoComputador.setText("Pontuação Computador: " + jogo.getComputador().getPontuacaoDoTime());
        labelStatus.setText(jogo.isTurnoDoJogador() ? "É a sua vez! Pokémon: " + jogo.getJogador().getPokemonPrincipal().getNome() : "Vez do Computador...");
        btnDica.setText("Dica (" + jogo.getDicasRestantes() + ")");

        for (int i = 0; i < TAMANHO_GRID; i++) {
            for (int j = 0; j < TAMANHO_GRID; j++) {
                JButton botao = botoesGrid[i][j];
                Celula celula = jogo.getTabuleiro().getGrade()[i][j];
                botao.setEnabled(!celula.isRevelada() && jogo.isTurnoDoJogador());
                if (celula.isRevelada()) {
                    if (celula.estaVazia()) {
                        botao.setText("");
                        botao.setBackground(Color.LIGHT_GRAY);
                    } else {
                        botao.setText(String.valueOf(celula.getPokemon().getNome().charAt(0)));
                    }
                }
            }
        }
    }
}