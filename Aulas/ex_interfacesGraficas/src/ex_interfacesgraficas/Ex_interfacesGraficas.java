package ex_interfacesgraficas;

/**
 *
 * @author lucas
 */

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Image;

public class Ex_interfacesGraficas {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Mapa Grid");
        JButton[][] botoes = new JButton[5][5];
        
        int l = 5;
        int c = 5;

        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(l, c));


        for (int i = 0; i < l; i++) { // i representa linhas
            for (int j = 0; j < c; j++) { // j representa colunas
                botoes[i][j] = new JButton();
                botoes[i][j].setEnabled(false);
                frame.add(botoes[i][j]);
            }
        }

        frame.setVisible(true);
        botoes[0][0].setEnabled(true);

        ImageIcon originalIcon = new ImageIcon("cavaleiro.jpg");
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        botoes[0][0].setIcon(scaledIcon);

    }
    
}
