package projetofinal;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JanelaBatalha extends JDialog implements ObservadorJogo {

    private Jogo jogo;
    private JLabel lblNomeJogador, lblEnergiaJogador;
    private JLabel lblNomeOponente, lblEnergiaOponente;
    private JTextArea txtLog;
    private JButton btnAtacar, btnTrocar, btnFugir;

    public JanelaBatalha(JFrame parent, Jogo jogo) {
        super(parent, "Batalha Pokémon!", true); 
        this.jogo = jogo;
        this.jogo.adicionarObservador(this);

        configurarJanela();
        atualizar("BATALHA_INICIADA", "A batalha começou!\n");
    }

    private void configurarJanela() {
        setSize(600, 500); 
        setLocationRelativeTo(getOwner());
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); 

        
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

        
        txtLog = new JTextArea();
        txtLog.setEditable(false);
        add(new JScrollPane(txtLog), BorderLayout.CENTER);

        
        JPanel painelAcoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAtacar = new JButton("Atacar");
        btnTrocar = new JButton("Trocar Pokémon");
        btnFugir = new JButton("Fugir");

        btnAtacar.addActionListener(e -> jogo.executarTurnoBatalha());
        btnFugir.addActionListener(e -> jogo.executarFugaBatalha());
        
        btnTrocar.addActionListener(e -> {
            Treinador jogador = jogo.getJogador();
            List<Pokemon> mochila = jogador.getMochila();

            if (mochila.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Sua mochila está vazia! Você não tem Pokémon para trocar.", "Mochila Vazia", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            String[] opcoes = new String[mochila.size()];
            for (int i = 0; i < mochila.size(); i++) {
                opcoes[i] = mochila.get(i).getNome() + " (Nível: " + mochila.get(i).getNivel() + ")";
            }

            String escolha = (String) JOptionPane.showInputDialog(this, "Escolha um Pokémon da mochila para lutar:", "Trocar Pokémon", JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

            if (escolha != null) {
                int indiceEscolhido = -1;
                for (int i = 0; i < opcoes.length; i++) {
                    if (opcoes[i].equals(escolha)) {
                        indiceEscolhido = i;
                        break;
                    }
                }
                if (indiceEscolhido != -1) {
                    jogo.executarTrocaPokemonBatalha(indiceEscolhido);
                }
            }
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