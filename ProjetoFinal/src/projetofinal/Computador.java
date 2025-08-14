package projetofinal;

/**
 * Representa o treinador controlado pelo computador.
 * [cite_start]Suas jogadas são geradas aleatoriamente e executadas em uma thread separada. [cite: 65, 142]
 */
public class Computador extends Treinador implements Runnable {

    public Computador(String nome) {
        super(nome);
    }

    /**
     * Lógica da jogada do computador, que será executada em uma nova thread.
     */
    @Override
    public void run() {
        // Simula o "tempo de pensar" do computador. [cite: 140]
        try {
            Thread.sleep(2000); // Ex: espera 2 segundos.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("O computador está fazendo sua jogada...");
        // Lógica para escolher uma célula aleatoriamente e realizar a ação.
    }
}
