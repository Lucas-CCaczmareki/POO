/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetofinal;

import javax.swing.SwingUtilities;

/**
 * Classe principal que inicia a aplicação.
 * Cria e exibe a janela de boas-vindas.
 */
public class Main {
    public static void main(String[] args) {
        // Garante que a interface gráfica seja executada na thread de despacho de eventos (EDT).
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Instancia e exibe a janela inicial do jogo.
                new JanelaBoasVindas().setVisible(true);
            }
        });
    }
}
