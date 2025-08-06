/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula15.pkg07_threads;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

class PainelCorrida extends JPanel {
    private Cavalo[] cavalos;

    public PainelCorrida(Cavalo[] cavalos) {
        this.cavalos = cavalos;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenha a pista
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Desenha os cavalos
        for (int i = 0; i < cavalos.length; i++) {
            Cavalo cavalo = cavalos[i];
            g.setColor(cavalo.getCor());
            int y = 50 + i * 50; // Posiciona cada cavalo em uma linha diferente
            int x = cavalo.getPosicao();
            g.fillOval(x, y, 30, 30); // Desenha o cavalo como um círculo
            g.drawString(cavalo.getNome(), x + 35, y + 20); // Exibe o nome do cavalo
        }
    }
}
    
