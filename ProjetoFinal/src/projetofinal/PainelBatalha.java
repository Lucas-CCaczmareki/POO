package projetofinal;

import javax.swing.*;
import java.awt.*;

public class PainelBatalha extends JPanel {
    //Atributos
    private JTextArea logBatalha;

    public PainelBatalha(Jogo jogo) {
        setLayout(new BorderLayout());

        logBatalha = new JTextArea("Aguardando batalha...");
        logBatalha.setEditable(false);

        JPanel painelAcoes = new JPanel();
        JButton botaoAtacar = new JButton("Atacar");
        JButton botaoFugir = new JButton("Fugir");

        painelAcoes.add(botaoAtacar);
        painelAcoes.add(botaoFugir);

        add(new JScrollPane(logBatalha), BorderLayout.CENTER);
        add(painelAcoes, BorderLayout.SOUTH);

        botaoAtacar.addActionListener(e -> jogo.executarRoundDeBatalha());
        botaoFugir.addActionListener(e -> jogo.tentarFugir());
    }

    public void atualizarLog(String mensagem) {
        logBatalha.append("\n" + mensagem);
    }

    public void setLogInicial(String mensagem) {
        logBatalha.setText(mensagem);
    }
}
