package projetofinal;

import javax.swing.*;
import java.awt.*;

public class JanelaBatalha extends JDialog implements ObservadorJogo {

    private Jogo jogo;
    private JLabel lblNomeJogador, lblEnergiaJogador;
    private JLabel lblNomeOponente, lblEnergiaOponente;
    private JTextArea txtLog;
    private JButton btnAtacar, btnTrocar, btnFugir;

    public JanelaBatalha(JFrame parent, Jogo jogo) {
        super(parent, "Batalha Pokémon!", true); // 'true' torna a janela modal
        this.jogo = jogo;
        this.jogo.adicionarObservador(this);

        configurarJanela();
        atualizar("BATALHA_INICIADA", "A batalha começou!\n");
    }

    private void configurarJanela() {
        setSize(600, 500); // Janela maior
        setLocationRelativeTo(getOwner());
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // Impede fechar no 'X'

        // Painel dos Pokémon (Topo)
        JPanel painelPokemons = new JPanel(new GridLayout(1, 2, 20, 0));
        painelPokemons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel pnlJogador = new JPanel(new BorderLayout());
        lblNomeJogador = new JLabel("", SwingConstants.CENTER);
        lblEnergiaJogador = new JLabel("", SwingConstants.CENTER);
        pnlJogador.add(lblNomeJogador, BorderLayout.NORTH);
        pnlJogador.add(lblEnergiaJogador, BorderLayout.SOUTH);
        pnlJogador.setBorder(BorderFactory.createTitledBorder("Seu Pokémon"));
        
        JPanel pnlOponente = new JPanel(new BorderLayout());
        lblNomeOponente = new JLabel("", SwingConstants.CENTER);
        lblEnergiaOponente = new JLabel("", SwingConstants.CENTER);
        pnlOponente.add(lblNomeOponente, BorderLayout.NORTH);
        pnlOponente.add(lblEnergiaOponente, BorderLayout.SOUTH);
        pnlOponente.setBorder(BorderFactory.createTitledBorder("Pokémon Oponente"));
        
        painelPokemons.add(pnlJogador);
        painelPokemons.add(pnlOponente);
        add(painelPokemons, BorderLayout.NORTH);

        // Log da Batalha (Centro)
        txtLog = new JTextArea();
        txtLog.setEditable(false);
        add(new JScrollPane(txtLog), BorderLayout.CENTER);

        // Painel de Ações (Baixo)
        JPanel painelAcoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAtacar = new JButton("Atacar");
        btnTrocar = new JButton("Trocar Pokémon");
        btnFugir = new JButton("Fugir");

        btnAtacar.addActionListener(e -> jogo.executarTurnoBatalha());
        btnFugir.addActionListener(e -> jogo.executarFugaBatalha());
        btnTrocar.addActionListener(e -> {
            // A lógica de troca será chamada aqui, mas por simplicidade, vamos deixar como placeholder
            JOptionPane.showMessageDialog(this, "Funcionalidade de troca em batalha a ser implementada.");
        });

        painelAcoes.add(btnAtacar);
        painelAcoes.add(btnTrocar);
        painelAcoes.add(btnFugir);
        add(painelAcoes, BorderLayout.SOUTH);
    }

    @Override
    public void atualizar(String evento, Object dados) {
        SwingUtilities.invokeLater(() -> {
            switch (evento) {
                case "BATALHA_ATUALIZADA":
                case "BATALHA_INICIADA":
                    Pokemon pJogador = jogo.getPokemonJogadorBatalha();
                    Pokemon pOponente = jogo.getPokemonOponenteBatalha();
                    
                    lblNomeJogador.setText(pJogador.getNome() + " (Nível " + pJogador.getNivel() + ")");
                    lblEnergiaJogador.setText("Energia: " + pJogador.getEnergia() + "/" + pJogador.getEnergiaMaxima());
                    lblNomeOponente.setText(pOponente.getNome() + " (Nível " + pOponente.getNivel() + ")");
                    lblEnergiaOponente.setText("Energia: " + pOponente.getEnergia() + "/" + pOponente.getEnergiaMaxima());
                    
                    if (dados instanceof String) {
                        txtLog.append((String) dados);
                    }
                    break;

                case "BATALHA_TERMINADA":
                    if (dados instanceof String) {
                        txtLog.append((String) dados);
                        JOptionPane.showMessageDialog(this, dados, "Fim da Batalha", JOptionPane.INFORMATION_MESSAGE);
                    }
                    jogo.removerObservador(this);
                    dispose();
                    break;
            }
        });
    }
}