package projetofinal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
 * Essa classe é responsável por gerenciar a janela que abre na opção de posicionar o pokémon
 */
public class JanelaPosicionamento extends JFrame {

    //Atributos
    private Jogo jogo;
    private JButton[][] botoesGrid;
    private JComboBox<String> comboPokemon;
    private Pokemon pokemonSelecionado;
    private JLabel labelInstrucao;
    private JButton btnJogar;
    private final int TAMANHO_GRID = 8;

    //Construtor
    public JanelaPosicionamento() {
        this.jogo = new Jogo(true); 
        configurarJanela();
        criarComponentes();
    }

    private void configurarJanela() {
        setTitle("Posicionar Time Pokémon");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void criarComponentes() {
        
        //Estético
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        //Cria o painel com as opçoes
        JPanel painelSuperior = new JPanel(new BorderLayout());
        labelInstrucao = new JLabel("1. Selecione seu Pokémon inicial e posicione-o na região correta.", SwingConstants.CENTER);
        labelInstrucao.setFont(new Font("Arial", Font.BOLD, 14));
        
        //Cria o painel que te permite selecionar um dos pokémons
        JPanel painelSelecao = new JPanel();
        comboPokemon = new JComboBox<>(PokemonFactory.getPokemonsDisponiveis());
        comboPokemon.addActionListener(e -> atualizarPokemonSelecionado());
        painelSelecao.add(new JLabel("Escolha seu Pokémon:"));
        painelSelecao.add(comboPokemon);
        painelSuperior.add(labelInstrucao, BorderLayout.NORTH);
        painelSuperior.add(painelSelecao, BorderLayout.CENTER);
        add(painelSuperior, BorderLayout.NORTH);

        /*
         * Cria um pseudo tabuleiro com botões clicávies dentro dessa janela 
         * para que o jogador consiga posicionar seu Pokémon.
         */
        JPanel painelTabuleiro = new JPanel(new GridLayout(TAMANHO_GRID, TAMANHO_GRID, 5, 5));
        botoesGrid = new JButton[TAMANHO_GRID][TAMANHO_GRID];
        for (int i = 0; i < TAMANHO_GRID; i++) {
            for (int j = 0; j < TAMANHO_GRID; j++) {
                final int linha = i;
                final int coluna = j;
                botoesGrid[i][j] = new JButton();
                botoesGrid[i][j].setOpaque(true); 
                botoesGrid[i][j].setContentAreaFilled(true);
                botoesGrid[i][j].setBorderPainted(false);
                botoesGrid[i][j].setMargin(new Insets(0, 0, 0, 0));
                colorirBotaoRegiao(botoesGrid[i][j], linha, coluna);
                botoesGrid[i][j].addActionListener(e -> clicarCelula(linha, coluna));
                painelTabuleiro.add(botoesGrid[i][j]);
            }
        }
        add(painelTabuleiro, BorderLayout.CENTER);
        
        //Cria o painel inferior com um botão para iniciar o jogo
        JPanel painelInferior = new JPanel();
        btnJogar = new JButton("Jogar");
        btnJogar.setFont(new Font("Arial", Font.BOLD, 16));
        btnJogar.setEnabled(false); 
        btnJogar.addActionListener(e -> iniciarJogo());
        painelInferior.add(btnJogar);
        add(painelInferior, BorderLayout.SOUTH);

        atualizarPokemonSelecionado();
    }

    //Cria uma instância de um pokémon toda vez que a opção para trocar for selecionada
    //Essas instâncias vão sendo criadas infinitamente até que sejam limpas pelo java
    //ou estourem a memória disponível
    private void atualizarPokemonSelecionado() {
        String nomePokemon = (String) comboPokemon.getSelectedItem();
        this.pokemonSelecionado = PokemonFactory.criarPokemon(nomePokemon);
    }

    /*
     * Quando o jogador clicar na célula desejada, posiciona o pokémon selecionado lá
     */
    private void clicarCelula(int linha, int coluna) {
        if (pokemonSelecionado == null || !comboPokemon.isEnabled()) return;

        try {
            jogo.getTabuleiro().posicionarPokemon(pokemonSelecionado, linha, coluna);
            
            JButton botaoClicado = botoesGrid[linha][coluna];
            botaoClicado.setIcon(PokemonImageManager.getPokemonImage(pokemonSelecionado.getNome()));
            botaoClicado.setDisabledIcon(PokemonImageManager.getPokemonImage(pokemonSelecionado.getNome()));
            
            jogo.getJogador().adicionarPokemonInicial(pokemonSelecionado);
            
            //Desabilita os outros botões e a seleção de pokémon 
            //(já que nesse ponto o jogador já selecionou)
            //(no estado atual não tem como voltar essa ação)
            comboPokemon.setEnabled(false);
            for (int i = 0; i < TAMANHO_GRID; i++) {
                for (int j = 0; j < TAMANHO_GRID; j++) {
                    botoesGrid[i][j].setEnabled(false);
                }
            }
            
            labelInstrucao.setText("2. Pokémon posicionado! O restante será distribuído. Clique em 'Jogar'.");
            posicionarRestante();
            btnJogar.setEnabled(true);

        //Captura uma exceção se o jogador quiser posicionar o pokémon numa posição inválida
        } catch (RegiaoInvalidaException | IllegalStateException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Posição Inválida", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
     * Escolhe o pokémon de forma aleatória e a aleatoriza a posição
     */
    private void posicionarRestante() {
        List<String> nomesRestantes = new ArrayList<>(Arrays.asList(PokemonFactory.getPokemonsDisponiveis()));
        nomesRestantes.remove(pokemonSelecionado.getNome());
        Collections.shuffle(nomesRestantes);
        
        Pokemon pComputador = PokemonFactory.criarPokemon(nomesRestantes.remove(0));
        jogo.getComputador().adicionarPokemonInicial(pComputador);
        posicionarAleatoriamente(pComputador);

        for (String nomeSelvagem : nomesRestantes) {
            Pokemon pSelvagem = PokemonFactory.criarPokemon(nomeSelvagem);
            posicionarAleatoriamente(pSelvagem);
            jogo.incrementarPokemonsSelvagens(); 
        }
    }

    /* faz a lógica de selecionar uma posição aleatória no tabuleiro */
    private void posicionarAleatoriamente(Pokemon pokemon) {
        while (true) {
            try {
                int linha = new Random().nextInt(jogo.getTabuleiro().getTamanhoN());
                int coluna = new Random().nextInt(jogo.getTabuleiro().getTamanhoN());
                if (jogo.getTabuleiro().getGrade()[linha][coluna].estaVazia()) {
                    jogo.getTabuleiro().posicionarPokemon(pokemon, linha, coluna);
                    return;
                }
            } catch (RegiaoInvalidaException | IllegalStateException e) { /* Tenta novamente */ }
        }
    }

    /* inicia a janela jogo com o tabuleiro que foi criado */
    private void iniciarJogo() {
        new JanelaJogo(this.jogo).setVisible(true);
        this.dispose();
    }
    
    private void colorirBotaoRegiao(JButton botao, int linha, int coluna) {
        int meio = TAMANHO_GRID / 2;
        if (linha < meio && coluna < meio) botao.setBackground(new Color(173, 216, 230)); // Água
        else if (linha < meio && coluna >= meio) botao.setBackground(new Color(144, 238, 144)); // Floresta
        else if (linha >= meio && coluna < meio) botao.setBackground(new Color(210, 180, 140)); // Terra
        else botao.setBackground(new Color(255, 255, 224)); // Elétrico
    }
}