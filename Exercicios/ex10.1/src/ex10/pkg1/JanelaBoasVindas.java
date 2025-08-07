package ex10.pkg1;

import javax.swing.*;
import java.awt.FlowLayout;


public class JanelaBoasVindas extends JFrame {
    //Construtor que monta a janela
    public JanelaBoasVindas() {
        setTitle("Boas-Vindas");    //seta o título da janela
        setSize(500, 500);   //seta o tamanho da janela

        //Define a ação que acontece quando o botão X da janela é clicado
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Centraliza a janela na tela
        setLocationRelativeTo(null);

        //Define como os componentes vão ser ORGANIZADOS dentro da janela
        //FlowLayout coloca cada um deles lado a lado;
        setLayout(new FlowLayout());

        // ---------- Adicionar os componentes -------------
        //Cria uma etiqueta de texto
        JLabel labelMessage = new JLabel("Olá, Mundo Gráfio");

        //Cria um botão
        JButton botaoSair = new JButton("Sair");
        
        //Configurando o botão
        botaoSair.addActionListener(e -> {
            //Aqui dentro vai o código que roda quando o botão é clicado
            JOptionPane.showMessageDialog(this, "Obrigado por usar o programa! Encerrando...");
            
            System.exit(0); //fecha o programa
        });

        //Adiciona o botão e a mensagem à Janela
        add(labelMessage);
        add(botaoSair);


    }


}
