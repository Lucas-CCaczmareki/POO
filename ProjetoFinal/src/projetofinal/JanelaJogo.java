package projetofinal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
/**
 * ATIVIDADE 2: Tela Principal do Jogo.
 * Esta é a versão final e corrigida da interface principal do jogo,
 * com todas as funcionalidades implementadas e conectadas à classe Jogo.
 */
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
                JButton botaoCelula = new JButton();
                botaoCelula.setOpaque(true); // Força o botão a pintar seu fundo
                botaoCelula.setContentAreaFilled(true);
                // Desliga a borda padrão do sistema, que costuma causar o problema
                botaoCelula.setBorderPainted(false);
                // --- FIM DA CORREÇÃO DEFINITIVA DE COR ---
                botaoCelula.setMargin(new Insets(0, 0, 0, 0));
                botaoCelula.setFont(new Font("Arial", Font.BOLD, 20));
                botaoCelula.addActionListener(e -> jogo.processarJogadaJogador(linha, coluna));
                colorirBotaoRegiao(botaoCelula, linha, coluna);
                botoesGrid[i][j] = botaoCelula;
                painelGrid.add(botaoCelula);
            }
        }
        add(painelGrid, BorderLayout.CENTER);

        // --- Painel Inferior (Botões de Opção) ---
        JPanel painelOpcoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        btnDica = new JButton("Dica (" + jogo.getDicasRestantes() + ")");
        JButton btnDebug = new JButton("Debug");
        JButton btnSair = new JButton("Sair");


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
                JOptionPane.showMessageDialog(this, dicaPositiva ? "Dica: Sim, existe um Pokémon nessa linha ou coluna!" : "Dica: Não, não há Pokémon nessa linha ou coluna.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
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

        
        painelOpcoes.add(btnDica);
        painelOpcoes.add(btnDebug);
        painelOpcoes.add(btnSair);
        add(painelOpcoes, BorderLayout.SOUTH);

        atualizar("INICIO", null);
    }

    private void colorirBotaoRegiao(JButton botao, int linha, int coluna) {
        int meio = TAMANHO_GRID / 2;
        if (linha < meio && coluna < meio) {
            botao.setBackground(new Color(173, 216, 230)); // Água
        } else if (linha < meio && coluna >= meio) {
            botao.setBackground(new Color(144, 238, 144)); // Floresta
        } else if (linha >= meio && coluna < meio) {
            botao.setBackground(new Color(210, 180, 140)); // Terra
        } else {
            botao.setBackground(new Color(255, 255, 224)); // Elétrico
        }
    }

    @Override
    public void atualizar(String evento, Object dados) {
        SwingUtilities.invokeLater(() -> {
            atualizarVisualizacaoGeral();
            Pokemon p;

            // NOVO CASE PARA INICIAR A JANELA DE BATALHA
            if ("BATALHA_INICIADA".equals(evento)) {
                new JanelaBatalha(this, jogo).setVisible(true);
            }
            
            switch (evento) {
                case "POKEMON_CAPTURADO":
                    Pokemon pCapturado = (Pokemon) dados;
                    JOptionPane.showMessageDialog(this, "Parabéns! Você capturou um " + pCapturado.getNome() + "!", "Captura Realizada!", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "CAPTURA_FALHOU":
                     Pokemon pFugitivo = (Pokemon) dados;
                     JOptionPane.showMessageDialog(this, "Ah, não! O " + pFugitivo.getNome() + " escapou da Pokébola!", "Captura Falhou", JOptionPane.WARNING_MESSAGE);
                    break;
                case "BATALHA_TERMINADA":
                    //não faz nada aqui, a JanelaBatalha já vai gerar uma mensagem
                    
                    //String logBatalha = (String) dados;
                    //JTextArea textArea = new JTextArea(logBatalha);
                    //JScrollPane scrollPane = new JScrollPane(textArea);  
                    //textArea.setLineWrap(true);  
                    //textArea.setWrapStyleWord(true); 
                    //scrollPane.setPreferredSize(new Dimension(400, 250));
                    //JOptionPane.showMessageDialog(this, scrollPane, "Resultado da Batalha", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "BATALHA_FUGA":
                    Treinador fujão = (Treinador) dados;
                    JOptionPane.showMessageDialog(this, fujão.getNome() + " fugiu da batalha!", "Fuga", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "FUGA_SEM_SAIDA":
                    Pokemon pEncurralado = (Pokemon) dados;
                    JOptionPane.showMessageDialog(this,
                    "O " + pEncurralado.getNome() + " tentou fugir, mas não encontrou para onde ir!",
                    "Pokémon Encurralado",
                    JOptionPane.INFORMATION_MESSAGE);
                // --- NOVAS MENSAGENS PARA O COMPUTADOR ---
                case "COMPUTADOR_CAPTUROU":
                    p = (Pokemon) dados;
                    JOptionPane.showMessageDialog(this, "O Computador capturou um " + p.getNome() + "!", "Ação do Oponente", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "COMPUTADOR_FALHOU_CAPTURA":
                    p = (Pokemon) dados;
                    JOptionPane.showMessageDialog(this, "O Computador tentou capturar, mas o " + p.getNome() + " escapou!", "Ação do Oponente", JOptionPane.WARNING_MESSAGE);
                    break;
                case "COMPUTADOR_DESAFIOU":
                    p = (Pokemon) dados;
                    JOptionPane.showMessageDialog(this, "O Computador encontrou seu " + p.getNome() + " e te desafiou para uma batalha!", "Batalha!", JOptionPane.WARNING_MESSAGE);
                    break;
            }

            if (jogo.isJogoTerminou()) {
                Treinador vencedor = jogo.getJogador().getPontuacao() >= jogo.getComputador().getPontuacao() ? jogo.getJogador() : jogo.getComputador();
                Treinador perdedor = vencedor == jogo.getJogador() ? jogo.getComputador() : jogo.getJogador();
                new JanelaFimDeJogo(vencedor, perdedor).setVisible(true);
                dispose();
            }
        });
    }

    private void atualizarVisualizacaoGeral() {
        labelPontuacaoJogador.setText("Sua Pontuação: " + jogo.getJogador().getPontuacao());
        labelPontuacaoComputador.setText("Pontuação Computador: " + jogo.getComputador().getPontuacao());
        labelStatus.setText(jogo.isTurnoDoJogador() ? "É a sua vez! Pokémon: " + jogo.getJogador().getPokemonPrincipal().getNome() : "Vez do Computador...");
        btnDica.setText("Dica (" + jogo.getDicasRestantes() + ")");

        boolean debug = jogo.isModoDebugAtivo();

        for (int i = 0; i < TAMANHO_GRID; i++) {
            for (int j = 0; j < TAMANHO_GRID; j++) {
                JButton botao = botoesGrid[i][j];
                Celula celula = jogo.getTabuleiro().getGrade()[i][j];
                
                // Habilita o botão apenas se a célula não foi revelada e for a vez do jogador
                botao.setEnabled(!celula.isRevelada() && jogo.isTurnoDoJogador());

                // Se a célula deve ser mostrada (ou por jogada ou pelo debug)
                if (celula.isRevelada() || debug) {
                    if (celula.estaVazia()) {
                        botao.setIcon(null);
                        botao.setText("");
                        // --- LÓGICA DE COR PARA CÉLULAS VAZIAS ---
                        Treinador quemRevelou = celula.getReveladaPor();
                        if (quemRevelou == jogo.getJogador()) {
                            botao.setBackground(new Color(173, 216, 230)); // Azul para o jogador
                        } else if (quemRevelou == jogo.getComputador()) {
                            botao.setBackground(new Color(255, 182, 193)); // Vermelho para o computador
                        } else {
                            botao.setBackground(Color.DARK_GRAY); // Cor padrão para vazias (ex: debug)
                        }
                    } else {
                        Pokemon p = celula.getPokemon();
                        ImageIcon icon = PokemonImageManager.getPokemonImage(p.getNome());
                        botao.setIcon(icon);
                        botao.setDisabledIcon(icon);
                        botao.setText(""); // CORRIGIDO: Garante que não há texto "?" com o ícone

                        // LÓGICA DE COR PARA O MODO DEBUG
                        if (debug) {
                            if (jogo.getJogador().getPokemonPrincipal() == p || jogo.getJogador().getMochila().contains(p)) {
                                botao.setBackground(new Color(102, 178, 255)); // Azul para jogador
                            } else if (jogo.getComputador().getPokemonPrincipal() == p || jogo.getComputador().getMochila().contains(p)) {
                                botao.setBackground(new Color(255, 102, 102)); // Vermelho para computador
                            } else {
                                // Mantém a cor da região para selvagens
                                colorirBotaoRegiao(botao, i, j);
                            }
                        }
                    }
                } else { // Se a célula está oculta e o debug está desligado
                    botao.setIcon(null);
                    botao.setText("?");
                    colorirBotaoRegiao(botao, i, j);
                }
            }
        }
    }
}