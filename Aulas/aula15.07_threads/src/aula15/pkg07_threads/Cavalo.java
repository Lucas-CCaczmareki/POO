/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula15.pkg07_threads;

import java.awt.Color;
import java.util.Random;

class Cavalo implements Runnable {
    private String nome;
    private int posicao;
    private Color cor;
    private static final int DISTANCIA_TOTAL = 1000;
    private static final int INTERVALO_ATUALIZACAO = 500; // 500 milissegundos
    private Random random;
    
    
    public Cavalo(String nome, Color cor) {
        this.nome = nome;
        this.posicao = 0;
        this.random = new Random();
        this.cor = cor;
    }
    
    @Override
    public void run() {
        while (posicao < DISTANCIA_TOTAL) {
            // Atualiza a velocidade aleatoriamente (entre 2 e 10 m/s)
            int velocidade = random.nextInt(16) + 5;

            posicao += velocidade;

            // Aguarda INTERVALO_ATUALIZACAO segundos antes da próxima atualização
            try {
                Thread.sleep(INTERVALO_ATUALIZACAO);
            } catch (InterruptedException e) {
                System.out.println(nome + " foi interrompido!");
            }
        }
        System.out.println(nome + " terminou a corrida!");
    }

    public int getPosicao() {
        return posicao;
    }

    public Color getCor() {
        return cor;
    }

    public String getNome() {
        return nome;
    }
}