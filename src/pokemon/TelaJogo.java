package pokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class TelaJogo extends JFrame {
    private Jogo jogo;
    private JButton[][] botoes;
    private JLabel lblPontuacaoJogador;
    private JLabel lblPontuacaoComputador;
    private JLabel lblPokemonPrincipal;
    private JComboBox<String> comboPokemon;
    private int dicasUsadas = 0;
    private boolean modoDebug = false;
    private Timer timer;
    
    public TelaJogo(Jogo jogo) {
        this.jogo = jogo;
        configurarJanela();
        criarComponentes();
        iniciarTimer();
    }
    
    private void configurarJanela() {
        setTitle("Pokémon Game - Jogando");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void criarComponentes() {
        setLayout(new BorderLayout());
        
        // Painel superior - Informações
        JPanel painelInfo = new JPanel(new GridLayout(2, 2, 10, 5));
        painelInfo.setBorder(BorderFactory.createTitledBorder("Informações do Jogo"));
        
        lblPontuacaoJogador = new JLabel("Jogador: 0 pontos");
        lblPontuacaoComputador = new JLabel("Computador: 0 pontos");
        lblPokemonPrincipal = new JLabel("Pokémon Principal: " + 
            (jogo.getJogador().getPokemonPrincipal() != null ? 
             jogo.getJogador().getPokemonPrincipal().getNome() : "Nenhum"));
        
        painelInfo.add(lblPontuacaoJogador);
        painelInfo.add(lblPontuacaoComputador);
        painelInfo.add(lblPokemonPrincipal);
        
        add(painelInfo, BorderLayout.NORTH);
        
        // Painel central - Tabuleiro
        JPanel painelTabuleiro = new JPanel(new GridLayout(8, 8, 2, 2));
        painelTabuleiro.setBorder(BorderFactory.createTitledBorder("Tabuleiro de Jogo"));
        
        botoes = new JButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                botoes[i][j] = new JButton();
                botoes[i][j].setPreferredSize(new Dimension(80, 80));
                final int linha = i;
                final int coluna = j;
                botoes[i][j].addActionListener(e -> clicarCelula(linha, coluna));
                
                // Colorir regiões
                colorirRegiao(i, j);
                
                painelTabuleiro.add(botoes[i][j]);
            }
        }
        
        add(painelTabuleiro, BorderLayout.CENTER);
        
        // Painel lateral - Controles
        JPanel painelControles = new JPanel();
        painelControles.setLayout(new BoxLayout(painelControles, BoxLayout.Y_AXIS));
        painelControles.setBorder(BorderFactory.createTitledBorder("Controles"));
        painelControles.setPreferredSize(new Dimension(200, 0));
        
        // Trocar Pokémon Principal
        JButton btnTrocar = new JButton("Trocar Pokémon Principal");
        btnTrocar.addActionListener(e -> trocarPokemon());
        
        // Dica
        JButton btnDica = new JButton("Dica (" + (3 - dicasUsadas) + " restantes)");
        btnDica.addActionListener(e -> usarDica());
        
        // Debug
        JButton btnDebug = new JButton("Debug");
        btnDebug.addActionListener(e -> alternarDebug());
        
        // Sair
        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> sairJogo());
        
        painelControles.add(btnTrocar);
        painelControles.add(Box.createVerticalStrut(10));
        painelControles.add(btnDica);
        painelControles.add(Box.createVerticalStrut(10));
        painelControles.add(btnDebug);
        painelControles.add(Box.createVerticalStrut(10));
        painelControles.add(btnSair);
        
        add(painelControles, BorderLayout.EAST);
        
        // Inicialmente todos os grids estão tapados
        atualizarInterface();
    }
    
    private void colorirRegiao(int linha, int coluna) {
        if (linha < 4 && coluna < 4) {
            botoes[linha][coluna].setBackground(new Color(173, 216, 230)); // Água
        } else if (linha < 4 && coluna >= 4) {
            botoes[linha][coluna].setBackground(new Color(144, 238, 144)); // Floresta
        } else if (linha >= 4 && coluna < 4) {
            botoes[linha][coluna].setBackground(new Color(210, 180, 140)); // Terra
        } else {
            botoes[linha][coluna].setBackground(new Color(211, 211, 211)); // Eletricidade
        }
    }
    
    private void clicarCelula(int linha, int coluna) {
        if (!jogo.isJogoAtivo()) return;
        
        Celula celula = jogo.getTabuleiro().getCelula(linha, coluna);
        if (celula.isRevelada()) return; // Célula já foi clicada
        
        Pokemon pokemonEncontrado = celula.getPokemon();
        
        if (pokemonEncontrado == null) {
            botoes[linha][coluna].setText("Vazio");
            botoes[linha][coluna].setBackground(Color.LIGHT_GRAY);
            JOptionPane.showMessageDialog(this, "Nada encontrado nesta posição!");
        } else if (pokemonEncontrado.isSelvagem()) {
            botoes[linha][coluna].setText("Pokémon Selvagem");
            botoes[linha][coluna].setBackground(Color.ORANGE);
            JOptionPane.showMessageDialog(this, "Pokémon selvagem encontrado: " + 
                pokemonEncontrado.getNome() + " (" + pokemonEncontrado.getTipo() + ") - Tentando capturar...");
            
            // Simula captura
            jogo.fazerJogadaJogador(linha, coluna);
        } else {
            // Pokémon do computador
            botoes[linha][coluna].setText("Pokémon Adversário");
            botoes[linha][coluna].setBackground(Color.YELLOW);
            JOptionPane.showMessageDialog(this, "Pokémon adversário encontrado: " + 
                pokemonEncontrado.getNome() + " (" + pokemonEncontrado.getTipo() + ") - Iniciando batalha...");
            
            // Simula batalha
            jogo.fazerJogadaJogador(linha, coluna);
        }
        
        atualizarInterface();
        
        // Verificar fim do jogo apenas se o jogo não está mais ativo
        if (!jogo.isJogoAtivo()) {
            mostrarFimJogo();
        }
    }
    
    private void trocarPokemon() {
        if (jogo.getJogador().getTime().size() <= 1) {
            JOptionPane.showMessageDialog(this, "Você só tem um Pokémon!");
            return;
        }
        
        String[] opcoes = new String[jogo.getJogador().getTime().size()];
        for (int i = 0; i < jogo.getJogador().getTime().size(); i++) {
            Pokemon p = jogo.getJogador().getTime().get(i);
            opcoes[i] = p.getNome() + " (Nível " + p.getNivel() + ")";
        }
        
        String selecao = (String) JOptionPane.showInputDialog(this, 
            "Escolha o Pokémon principal:", "Trocar Pokémon", 
            JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
        
        if (selecao != null) {
            for (int i = 0; i < opcoes.length; i++) {
                if (opcoes[i].equals(selecao)) {
                    jogo.getJogador().trocarPokemonPrincipal(i);
                    break;
                }
            }
            atualizarInterface();
        }
    }
    
    private void usarDica() {
        if (dicasUsadas >= 3) {
            JOptionPane.showMessageDialog(this, "Você já usou todas as dicas!");
            return;
        }
        
        // Primeiro input - Linha
        String linhaStr = JOptionPane.showInputDialog(this, 
            "Digite a linha (0-7) para a dica:");
        
        if (linhaStr == null) return; // Usuário cancelou
        
        try {
            int linha = Integer.parseInt(linhaStr.trim());
            if (linha < 0 || linha >= 8) {
                JOptionPane.showMessageDialog(this, "Linha deve estar entre 0 e 7!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Segundo input - Coluna
            String colunaStr = JOptionPane.showInputDialog(this, 
                "Digite a coluna (0-7) para a dica:");
            
            if (colunaStr == null) return; // Usuário cancelou
            
            int coluna = Integer.parseInt(colunaStr.trim());
            if (coluna < 0 || coluna >= 8) {
                JOptionPane.showMessageDialog(this, "Coluna deve estar entre 0 e 7!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Verificar se existe Pokémon na posição específica
            Pokemon pokemon = jogo.getTabuleiro().getPokemon(linha, coluna);
            boolean encontrou = pokemon != null;
            
            JOptionPane.showMessageDialog(this, 
                encontrou ? "Sim! Existe um Pokémon nesta posição (" + linha + "," + coluna + ")!" : 
                           "Não! Não há nenhum Pokémon nesta posição (" + linha + "," + coluna + ").",
                "Resultado da Dica", JOptionPane.INFORMATION_MESSAGE);
            
            dicasUsadas++;
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Digite um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void alternarDebug() {
        modoDebug = !modoDebug;
        
        if (modoDebug) {
            // Revelar todas as células não reveladas pelo jogador
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Celula celula = jogo.getTabuleiro().getCelula(i, j);
                    if (!celula.isReveladaPeloJogador()) {
                        celula.setRevelada(true);
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Modo DEBUG ATIVADO - Todos os Pokémons revelados!");
        } else {
            // Ocultar células que não foram reveladas pelo jogador
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Celula celula = jogo.getTabuleiro().getCelula(i, j);
                    if (!celula.isReveladaPeloJogador()) {
                        celula.setRevelada(false);
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Modo DEBUG DESATIVADO - Pokémons ocultados!");
        }
        
        atualizarInterface();
    }
    
    private void sairJogo() {
        int opcao = JOptionPane.showConfirmDialog(this, 
            "Deseja salvar o jogo antes de sair?", "Sair", 
            JOptionPane.YES_NO_CANCEL_OPTION);
        
        if (opcao == JOptionPane.YES_OPTION) {
            // TODO: Implementar salvamento
            JOptionPane.showMessageDialog(this, "Funcionalidade de salvamento será implementada!");
        }
        
        if (opcao != JOptionPane.CANCEL_OPTION) {
            jogo.encerrarJogo();
            TelaBoasVindas telaInicial = new TelaBoasVindas();
            telaInicial.setVisible(true);
            this.dispose();
        }
    }
    
    private void atualizarInterface() {
        lblPontuacaoJogador.setText("Jogador: " + jogo.getJogador().getPontuacao() + " pontos");
        lblPontuacaoComputador.setText("Computador: " + jogo.getComputador().getPontuacao() + " pontos");
        
        Pokemon principal = jogo.getJogador().getPokemonPrincipal();
        if (principal != null) {
            lblPokemonPrincipal.setText("Pokémon Principal: " + principal.getNome() + 
                " (Nível " + principal.getNivel() + ", Energia: " + principal.getEnergia() + ")");
        }
        
        // Atualizar visualização do grid baseado no estado das células
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Celula celula = jogo.getTabuleiro().getCelula(i, j);
                JButton botao = botoes[i][j];
                
                if (celula.isRevelada()) {
                    // Célula revelada - mostrar conteúdo
                    if (celula.estaVazia()) {
                        botao.setText("Vazio");
                        botao.setBackground(Color.LIGHT_GRAY);
                    } else {
                        Pokemon p = celula.getPokemon();
                        if (p.isSelvagem()) {
                            botao.setText("Pokémon Selvagem");
                            botao.setBackground(Color.ORANGE);
                        } else {
                            botao.setText("Pokémon Adversário");
                            botao.setBackground(Color.YELLOW);
                        }
                    }
                } else {
                    // Célula não revelada - mostrar "?"
                    botao.setText("?");
                    colorirRegiao(i, j);
                }
            }
        }
    }
    
    private void mostrarPokemonsJogador() {
        // Mostrar Pokémons do jogador no tabuleiro
        for (Pokemon pokemon : jogo.getJogador().getTime()) {
            // Procurar onde o Pokémon está no tabuleiro
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Pokemon p = jogo.getTabuleiro().getPokemon(i, j);
                    if (p != null && p.getNome().equals(pokemon.getNome())) {
                        botoes[i][j].setText(pokemon.getNome());
                        botoes[i][j].setBackground(Color.GREEN); // Verde para Pokémons do jogador
                        botoes[i][j].setEnabled(false);
                        System.out.println("Pokémon do jogador encontrado: " + pokemon.getNome() + " em (" + i + "," + j + ")");
                    }
                }
            }
        }
    }
    
    private void iniciarTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> atualizarInterface());
            }
        }, 1000, 1000);
    }
    
    private void mostrarFimJogo() {
        jogo.encerrarJogo();
        timer.cancel();
        
        Treinador vencedor = jogo.getVencedor();
        String mensagem;
        
        if (vencedor != null) {
            mensagem = "Vencedor: " + vencedor.getNome() + " com " + vencedor.getPontuacao() + " pontos!";
        } else {
            mensagem = "Empate! Ambos os treinadores têm " + jogo.getJogador().getPontuacao() + " pontos.";
        }
        
        JOptionPane.showMessageDialog(this, mensagem, "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
        
        int opcao = JOptionPane.showConfirmDialog(this, 
            "Deseja jogar novamente?", "Novo Jogo", 
            JOptionPane.YES_NO_OPTION);
        
        if (opcao == JOptionPane.YES_OPTION) {
            TelaBoasVindas telaInicial = new TelaBoasVindas();
            telaInicial.setVisible(true);
        }
        
        this.dispose();
    }
}
