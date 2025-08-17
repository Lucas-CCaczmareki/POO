package projetofinal;

import javax.swing.*;
import java.awt.*;

public class PainelMenu extends JPanel {
    //Construtor
    public PainelMenu(Jogo jogo) {
        setLayout(new GridBagLayout()); //centraliza o botão

        JButton botaoIniciar = new JButton("Iniciar novo jogo");

        botaoIniciar.addActionListener(e -> {
            //Quando clicado, avisa o jogo pra começar
            jogo.iniciarNovoJogo();
        });

        add(botaoIniciar);
    }
}
