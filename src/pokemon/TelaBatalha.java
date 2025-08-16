package pokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaBatalha extends JDialog {
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private JLabel lblPokemon1, lblPokemon2;
    private JLabel lblEnergia1, lblEnergia2;
    private JTextArea txtLog;
    private JButton btnFugir;
    private boolean batalhaAtiva;
    private Jogo jogo;
    
    public TelaBatalha(JFrame parent, Pokemon pokemon1, Pokemon pokemon2, Jogo jogo) {
        super(parent, "Batalha Pokémon", true);
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.jogo = jogo;
        this.batalhaAtiva = true;
        
        configurarInterface();
        iniciarBatalha();
    }
    
    private void configurarInterface() {
        setLayout(new BorderLayout());
        setSize(600, 400);
        setLocationRelativeTo(getOwner());
        
        // Painel superior com os Pokémon
        JPanel painelPokemons = new JPanel(new GridLayout(1, 2, 10, 10));
        painelPokemons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Pokémon 1
        JPanel panel1 = new JPanel(new BorderLayout());
        lblPokemon1 = new JLabel(pokemon1.getNome() + " (Nível " + pokemon1.getNivel() + ")");
        lblPokemon1.setHorizontalAlignment(SwingConstants.CENTER);
        lblEnergia1 = new JLabel("Energia: " + pokemon1.getEnergia());
        lblEnergia1.setHorizontalAlignment(SwingConstants.CENTER);
        
        panel1.add(lblPokemon1, BorderLayout.NORTH);
        panel1.add(lblEnergia1, BorderLayout.SOUTH);
        panel1.setBorder(BorderFactory.createTitledBorder("Pokémon 1"));
        
        // Pokémon 2
        JPanel panel2 = new JPanel(new BorderLayout());
        lblPokemon2 = new JLabel(pokemon2.getNome() + " (Nível " + pokemon2.getNivel() + ")");
        lblPokemon2.setHorizontalAlignment(SwingConstants.CENTER);
        lblEnergia2 = new JLabel("Energia: " + pokemon2.getEnergia());
        lblEnergia2.setHorizontalAlignment(SwingConstants.CENTER);
        
        panel2.add(lblPokemon2, BorderLayout.NORTH);
        panel2.add(lblEnergia2, BorderLayout.SOUTH);
        panel2.setBorder(BorderFactory.createTitledBorder("Pokémon 2"));
        
        painelPokemons.add(panel1);
        painelPokemons.add(panel2);
        
        // Área de log
        txtLog = new JTextArea();
        txtLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtLog);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Log da Batalha"));
        
        // Botão fugir
        btnFugir = new JButton("Fugir");
        btnFugir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                batalhaAtiva = false;
                adicionarLog("Você fugiu da batalha!");
                dispose();
            }
        });
        
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnFugir);
        
        add(painelPokemons, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }
    
    private void iniciarBatalha() {
        adicionarLog("Batalha iniciada: " + pokemon1.getNome() + " vs " + pokemon2.getNome());
        
        while (batalhaAtiva && pokemon1.getEnergia() > 0 && pokemon2.getEnergia() > 0) {
            // Ataque do Pokémon 1
            if (pokemon1.getEnergia() > 0) {
                int dano = pokemon1.calcularDano();
                pokemon2.receberDano(dano);
                adicionarLog(pokemon1.getNome() + " causou " + dano + " de dano!");
                atualizarEnergia();
                
                if (pokemon2.getEnergia() <= 0) break;
            }
            
            // Pequena pausa
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            
            // Ataque do Pokémon 2
            if (pokemon2.getEnergia() > 0) {
                int dano = pokemon2.calcularDano();
                pokemon1.receberDano(dano);
                adicionarLog(pokemon2.getNome() + " causou " + dano + " de dano!");
                atualizarEnergia();
            }
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        
        // Determina vencedor
        Pokemon vencedor = pokemon1.getEnergia() > 0 ? pokemon1 : pokemon2;
        adicionarLog(vencedor.getNome() + " venceu a batalha!");
        
        // Restaura energia
        pokemon1.restaurarEnergia();
        pokemon2.restaurarEnergia();
        
        JOptionPane.showMessageDialog(this, 
            vencedor.getNome() + " venceu a batalha!", 
            "Fim da Batalha", 
            JOptionPane.INFORMATION_MESSAGE);
        
        dispose();
    }
    
    private void adicionarLog(String mensagem) {
        SwingUtilities.invokeLater(() -> {
            txtLog.append(mensagem + "\n");
            txtLog.setCaretPosition(txtLog.getDocument().getLength());
        });
    }
    
    private void atualizarEnergia() {
        SwingUtilities.invokeLater(() -> {
            lblEnergia1.setText("Energia: " + pokemon1.getEnergia());
            lblEnergia2.setText("Energia: " + pokemon2.getEnergia());
        });
    }
}
