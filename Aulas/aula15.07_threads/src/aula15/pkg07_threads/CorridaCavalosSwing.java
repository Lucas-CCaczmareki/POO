/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula15.pkg07_threads;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class CorridaCavalosSwing {
    public static void main(String[] args) {


        // Cria a janela
        JFrame frame = new JFrame("Corrida de Cavalos");
        frame.setSize(1200, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Cria os cavalos
        Cavalo[] cavalos = new Cavalo[4];
        cavalos[0] = new Cavalo("Cavalo 1", Color.RED);
        cavalos[1] = new Cavalo("Cavalo 2", Color.BLUE);
        cavalos[2] = new Cavalo("Cavalo 3", Color.GREEN);
        cavalos[3] = new Cavalo("Cavalo 4", Color.ORANGE);

        // Cria o painel da corrida e adiciona à janela
        PainelCorrida painel = new PainelCorrida(cavalos);
        frame.add(painel);

        // Exibe a janela
        frame.setVisible(true);

        // Cria um Timer para atualizar a tela a cada 100 milissegundos
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> painel.repaint());
            }
        }, 0, 100); // Inicia imediatamente (delay = 0) e repete a cada 100 ms
        
        // Inicia a corrida
        for (Cavalo cavalo : cavalos) {
            new Thread(cavalo).start();
        }
    }
}