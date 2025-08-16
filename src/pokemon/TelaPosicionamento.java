package pokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPosicionamento extends JFrame {
    private Jogo jogo;
    private JButton[][] botoes;
    private JComboBox<String> comboPokemon;
    private Pokemon pokemonSelecionado;
    private int pokemonsPosicionados = 0;
    
    public TelaPosicionamento(Jogo jogo) {
        this.jogo = jogo;
        configurarJanela();
        criarComponentes();
    }
    
    private void configurarJanela() {
        setTitle("Posicionar Time Pokémon");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void criarComponentes() {
        setLayout(new BorderLayout());
        
        // Painel superior
        JPanel painelSuperior = new JPanel();
        painelSuperior.setBorder(BorderFactory.createTitledBorder("Selecionar Pokémon"));
        
        String[] pokemons = {"Psyduck (Água)", "Sandshrew (Terra)"};
        comboPokemon = new JComboBox<>(pokemons);
        comboPokemon.addActionListener(e -> selecionarPokemon());
        
        JButton btnDebug = new JButton("DEBUG - Posicionar Todos");
        btnDebug.addActionListener(e -> posicionarTodos());
        
        painelSuperior.add(new JLabel("Pokémon:"));
        painelSuperior.add(comboPokemon);
        painelSuperior.add(btnDebug);
        
        add(painelSuperior, BorderLayout.NORTH);
        
        // Tabuleiro
        JPanel painelTabuleiro = new JPanel(new GridLayout(8, 8, 2, 2));
        painelTabuleiro.setBorder(BorderFactory.createTitledBorder("Tabuleiro 8x8"));
        
        botoes = new JButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                botoes[i][j] = new JButton();
                botoes[i][j].setPreferredSize(new Dimension(60, 60));
                final int linha = i;
                final int coluna = j;
                botoes[i][j].addActionListener(e -> clicarCelula(linha, coluna));
                
                // Colorir regiões
                colorirRegiao(i, j);
                
                painelTabuleiro.add(botoes[i][j]);
            }
        }
        
        add(painelTabuleiro, BorderLayout.CENTER);
        
        // Painel inferior
        JPanel painelInferior = new JPanel();
        JButton btnJogar = new JButton("Jogar");
        btnJogar.addActionListener(e -> iniciarJogo());
        painelInferior.add(btnJogar);
        
        add(painelInferior, BorderLayout.SOUTH);
        
        // Selecionar primeiro Pokémon
        selecionarPokemon();
    }
    
    private void colorirRegiao(int linha, int coluna) {
        if (linha < 4 && coluna < 4) {
            botoes[linha][coluna].setBackground(new Color(173, 216, 230)); // Água - azul claro
        } else if (linha < 4 && coluna >= 4) {
            botoes[linha][coluna].setBackground(new Color(144, 238, 144)); // Floresta - verde claro
        } else if (linha >= 4 && coluna < 4) {
            botoes[linha][coluna].setBackground(new Color(210, 180, 140)); // Terra - marrom claro
        } else {
            botoes[linha][coluna].setBackground(new Color(211, 211, 211)); // Eletricidade - cinza claro
        }
    }
    
    private void selecionarPokemon() {
        String selecao = (String) comboPokemon.getSelectedItem();
        if (selecao.contains("Psyduck")) {
            pokemonSelecionado = new PokemonAgua("Psyduck");
        } else if (selecao.contains("Sandshrew")) {
            pokemonSelecionado = new PokemonTerra("Sandshrew");
        }
    }
    
    private void clicarCelula(int linha, int coluna) {
        if (pokemonSelecionado == null) return;
        
        try {
            jogo.getTabuleiro().posicionarPokemon(pokemonSelecionado, linha, coluna);
            
            // Marcar posição
            botoes[linha][coluna].setText(pokemonSelecionado.getNome());
            botoes[linha][coluna].setEnabled(false);
            
            // Adicionar ao time do jogador
            jogo.getJogador().adicionarPokemon(pokemonSelecionado);
            
            pokemonsPosicionados++;
            
            // Verificar se posicionou todos
            if (pokemonsPosicionados >= 2) {
                posicionarPokemonsRestantes();
            }
            
        } catch (RegiaoInvalidaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void posicionarPokemonsRestantes() {
        // Posiciona Pokémon do computador
        Pokemon pokemonComputador = new PokemonTerra("Sandshrew");
        jogo.getComputador().adicionarPokemon(pokemonComputador);
        
        // Posiciona Pokémons selvagens
        Pokemon[] pokemons = {
            new PokemonAgua("Wartortle"),
            new PokemonFloresta("Bulbasaur"),
            new PokemonFloresta("Caterpie"),
            new PokemonEletrico("Raichu"),
            new PokemonEletrico("Pikachu"),
            new PokemonTerra("Sandslash")
        };
        
        for (Pokemon pokemon : pokemons) {
            jogo.getTabuleiro().posicionarPokemonAleatorio(pokemon);
        }
    }
    
    private void posicionarTodos() {
        // Posiciona automaticamente todos os Pokémons
        try {
            // Pokémon do jogador
            Pokemon psyduck = new PokemonAgua("Psyduck");
            jogo.getTabuleiro().posicionarPokemon(psyduck, 0, 3);
            jogo.getJogador().adicionarPokemon(psyduck);
            botoes[0][3].setText("Psyduck");
            botoes[0][3].setEnabled(false);
            
            // Pokémon do computador (escondido no tabuleiro)
            Pokemon sandshrew = new PokemonTerra("Sandshrew");
            jogo.getTabuleiro().posicionarPokemon(sandshrew, 4, 0);
            jogo.getComputador().adicionarPokemon(sandshrew);
            // NÃO mostra o Pokémon do computador na tela de posicionamento
            // Ele ficará escondido até ser descoberto pelo jogador
            
            // Pokémons selvagens baseados na imagem
            Pokemon[] pokemons = {
                new PokemonFloresta("Bulbasaur"), // 1,5
                new PokemonAgua("Wartortle"),     // 2,1
                new PokemonFloresta("Caterpie"),  // 3,6
                new PokemonEletrico("Raichu"),    // 5,5
                new PokemonEletrico("Pikachu"),   // 6,4
                new PokemonTerra("Sandslash")     // 7,2
            };
            
            int[][] posicoes = {{1,5}, {2,1}, {3,6}, {5,5}, {6,4}, {7,2}};
            
            for (int i = 0; i < pokemons.length; i++) {
                jogo.getTabuleiro().posicionarPokemon(pokemons[i], posicoes[i][0], posicoes[i][1]);
                botoes[posicoes[i][0]][posicoes[i][1]].setText(pokemons[i].getNome());
                botoes[posicoes[i][0]][posicoes[i][1]].setEnabled(false);
            }
            
            pokemonsPosicionados = 2;
            
        } catch (RegiaoInvalidaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void iniciarJogo() {
        if (pokemonsPosicionados < 2) {
            JOptionPane.showMessageDialog(this, "Posicione pelo menos 2 Pokémons antes de iniciar!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        jogo.iniciarJogo();
        TelaJogo telaJogo = new TelaJogo(jogo);
        telaJogo.setVisible(true);
        this.dispose();
    }
}
