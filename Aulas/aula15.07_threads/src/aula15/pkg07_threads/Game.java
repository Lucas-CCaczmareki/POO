package aula15.pkg07_threads;
import java.util.Random;

// Classe que representa o Herói
class Hero {
    private int x, y;

    public Hero(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        System.out.println("Herói moveu para: (" + x + ", " + y + ")");
    }
}

// Classe que representa um Inimigo
class Enemy implements Runnable {
    private int x, y;
    private static final Random random = new Random();

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {
        while (true) {
            // Move o inimigo aleatoriamente
            int dx = random.nextInt(3) - 1; // -1, 0 ou 1
            int dy = random.nextInt(3) - 1; // -1, 0 ou 1
            x += dx;
            y += dy;
            System.out.println("Inimigo moveu para: (" + x + ", " + y + ")");

            try {
                Thread.sleep(1000); // Espera 1 segundo antes de mover novamente
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Classe principal do jogo
public class Game {
    public static void main(String[] args) {
        Hero hero = new Hero(0, 0);

        // Cria e inicia threads para os inimigos
        Thread enemy1 = new Thread(new Enemy(5, 5));
        Thread enemy2 = new Thread(new Enemy(10, 10));

        enemy1.start();
        enemy2.start();

        // Simula o movimento do herói
        for (int i = 0; i < 5; i++) {
            hero.move(1, 1);
            try {
                Thread.sleep(2000); // Espera 2 segundos antes de mover o herói novamente
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
