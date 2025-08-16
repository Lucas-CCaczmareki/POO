package pokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaBoasVindas extends JFrame {
    private Jogo jogo;
    
    public TelaBoasVindas() {
        this.jogo = new Jogo();
        configurarJanela();
        criarComponentes();
    }
    
    private void configurarJanela() {
        setTitle("Pokémon Game - Boas Vindas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    private void criarComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Título
        JLabel titulo = new JLabel("Bem-vindo ao Pokémon Game!");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);
        
        panel.add(Box.createVerticalStrut(20));
        
        // Subtítulo
        JLabel subtitulo = new JLabel("Escolha como deseja iniciar:");
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(subtitulo);
        
        panel.add(Box.createVerticalStrut(30));
        
        // Botões
        JButton btnCarregar = new JButton("Carregar Jogo Salvo");
        btnCarregar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCarregar.setMaximumSize(new Dimension(200, 40));
        btnCarregar.addActionListener(e -> carregarJogo());
        
        JButton btnPosicionar = new JButton("Posicionar Time Pokémon");
        btnPosicionar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPosicionar.setMaximumSize(new Dimension(200, 40));
        btnPosicionar.addActionListener(e -> posicionarTime());
        
        JButton btnAleatorio = new JButton("Distribuição Aleatória");
        btnAleatorio.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAleatorio.setMaximumSize(new Dimension(200, 40));
        btnAleatorio.addActionListener(e -> distribuicaoAleatoria());
        
        panel.add(btnCarregar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnPosicionar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnAleatorio);
        
        add(panel);
    }
    
    private void carregarJogo() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            // TODO: Implementar carregamento de arquivo
            JOptionPane.showMessageDialog(this, "Funcionalidade de carregamento será implementada!");
        }
    }
    
    private void posicionarTime() {
        TelaPosicionamento telaPos = new TelaPosicionamento(jogo);
        telaPos.setVisible(true);
        this.dispose();
    }
    
    private void distribuicaoAleatoria() {
        // Cria Pokémons iniciais para cada treinador
        Pokemon pokemonJogador = new PokemonAgua("Psyduck");
        Pokemon pokemonComputador = new PokemonTerra("Sandshrew");
        
        // Posiciona Pokémons no tabuleiro primeiro
        try {
            jogo.getTabuleiro().posicionarPokemon(pokemonJogador, 0, 3); // Posição fixa para o jogador
            jogo.getTabuleiro().posicionarPokemon(pokemonComputador, 4, 0); // Posição fixa para o computador
        } catch (RegiaoInvalidaException e) {
            // Se falhar, posiciona aleatoriamente
            jogo.getTabuleiro().posicionarPokemonAleatorio(pokemonJogador);
            jogo.getTabuleiro().posicionarPokemonAleatorio(pokemonComputador);
        }
        
        // Adiciona aos times
        jogo.getJogador().adicionarPokemon(pokemonJogador);
        jogo.getComputador().adicionarPokemon(pokemonComputador);
        
        // Posiciona Pokémons selvagens aleatoriamente
        posicionarPokemonsAleatorios();
        
        // Debug: mostrar quantos Pokémons foram posicionados
        System.out.println("Total de Pokémons no tabuleiro: " + jogo.getTabuleiro().contarPokemons());
        System.out.println("Pokémons selvagens: " + jogo.getTabuleiro().contarPokemonsSelvagens());
        System.out.println("Pokémon do jogador posicionado em: (0,3)");
        System.out.println("Pokémon do computador posicionado em: (4,0)");
        
        // Inicia o jogo
        jogo.iniciarJogo();
        
        TelaJogo telaJogo = new TelaJogo(jogo);
        telaJogo.setVisible(true);
        this.dispose();
    }
    
    private void posicionarPokemonsAleatorios() {
        // Lista de Pokémons selvagens baseada na imagem
        Pokemon[] pokemons = {
            new PokemonAgua("Wartortle"),
            new PokemonFloresta("Bulbasaur"),
            new PokemonFloresta("Caterpie"),
            new PokemonEletrico("Raichu"),
            new PokemonEletrico("Pikachu"),
            new PokemonTerra("Sandslash"),
            new PokemonAgua("Squirtle"),
            new PokemonFloresta("Oddish"),
            new PokemonEletrico("Voltorb"),
            new PokemonTerra("Diglett")
        };
        
        for (Pokemon pokemon : pokemons) {
            jogo.getTabuleiro().posicionarPokemonAleatorio(pokemon);
        }
        
        System.out.println("Pokémons selvagens posicionados: " + pokemons.length);
    }
}
