package projetofinal;

import javax.swing.*;
import java.awt.*;

public class JanelaPrincipal extends JFrame {
    //Atributos
    private CardLayout cardLayout;
    private JPanel painelPrincipal;
    private Jogo jogo;

    //Aqui vai os paines que vamos usar
    private PainelMenu painelMenu;
    private PainelTabuleiro painelTabuleiro;
    private PainelBatalha painelBatalha;

    //Construtor
    public JanelaPrincipal(){
        setTitle("Pokémon - Batalha de Tabuleiro");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Config do card layout
        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);


        //Passamos this pro jogo se comunicar com essa janela
        jogo = new Jogo(this);

        //Criação das telas (painéis)
        painelMenu = new PainelMenu(jogo);
        painelTabuleiro = new PainelTabuleiro(jogo);
        painelBatalha = new PainelBatalha(jogo);

        // Adiciona os paineis
        painelPrincipal.add(painelMenu, "MENU");
        painelPrincipal.add(painelTabuleiro, "TABULEIRO");
        painelPrincipal.add(painelBatalha, "BATALHA");

        add(painelPrincipal);
    }

    //Métodos pra trocar a tela
    public void mostrarPainel(String nomeDoPainel) {
        cardLayout.show(painelPrincipal, nomeDoPainel);
    }

    public PainelBatalha getPainelBatalha() {
        return painelBatalha;
    }

    //Getters e setters
    public PainelTabuleiro getPainelTabuleiro() {
        return painelTabuleiro;
    }
}
