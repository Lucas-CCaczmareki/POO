package pokemon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * ATIVIDADE 3: Janela de Fim de Jogo.
 * Exibe o vencedor da partida, o placar final e as opções para
 * iniciar um novo jogo ou fechar a aplicação.
 */
public class JanelaFimDeJogo extends JFrame {

    /**
     * Construtor da janela de fim de jogo.
     * @param vencedor O treinador que venceu a partida.
     * @param perdedor O treinador que perdeu a partida.
     */
    public JanelaFimDeJogo(Treinador vencedor, Treinador perdedor) {
        // --- Configurações da Janela ---
        setTitle("Fim da Partida!");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20));

        // --- Mensagem de Vitória ---
        JLabel labelVencedor = new JLabel("A equipe vencedora é " + vencedor.getNome() + "!", SwingConstants.CENTER);
        labelVencedor.setFont(new Font("Arial", Font.BOLD, 18));
        add(labelVencedor, BorderLayout.NORTH);

        // --- Placar Final ---
        JPanel painelPlacar = new JPanel(new GridLayout(2, 1));
        JLabel labelMensagemPlacar = new JLabel("Placar Final:", SwingConstants.CENTER);
        JLabel labelPlacar = new JLabel(
                vencedor.getNome() + ": " + vencedor.getPontuacao() + " | " +
                perdedor.getNome() + ": " + perdedor.getPontuacao(),
                SwingConstants.CENTER);
        labelPlacar.setFont(new Font("Arial", Font.BOLD, 16));
        painelPlacar.add(labelMensagemPlacar);
        painelPlacar.add(labelPlacar);
        add(painelPlacar, BorderLayout.CENTER);

        // --- Botões de Opção ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        JButton botaoNovoJogo = new JButton("Novo Jogo");
        JButton botaoFechar = new JButton("Fechar");
        painelBotoes.add(botaoNovoJogo);
        painelBotoes.add(botaoFechar);
        add(painelBotoes, BorderLayout.SOUTH);

        // --- Ações dos Botões ---
        botaoNovoJogo.addActionListener(e -> {
            dispose();
            new JanelaBoasVindas().setVisible(true);
        });

        botaoFechar.addActionListener(e -> System.exit(0));
        
        // --- Salva o resultado no histórico ---
        salvarResultadoNoHistorico(vencedor, perdedor);
    }

    /**
     * Chama o GerenciadorDeArquivos para salvar o resultado desta partida.
     */
    private void salvarResultadoNoHistorico(Treinador vencedor, Treinador perdedor) {
        GerenciadorDeArquivos gerenciador = new GerenciadorDeArquivos();
        
        // Determina quem é o jogador 1 e 2 para o log
        String jogador1Nome = vencedor.getNome().equals("Jogador 1") ? vencedor.getNome() : perdedor.getNome();
        int jogador1Pontos = vencedor.getNome().equals("Jogador 1") ? vencedor.getPontuacao() : perdedor.getPontuacao();
        
        String jogador2Nome = vencedor.getNome().equals("Computador") ? vencedor.getNome() : perdedor.getNome();
        int jogador2Pontos = vencedor.getNome().equals("Computador") ? vencedor.getPontuacao() : perdedor.getPontuacao();

        gerenciador.salvarHistorico(
            jogador1Nome, jogador1Pontos,
            jogador2Nome, jogador2Pontos,
            vencedor.getNome()
        );
    }
}