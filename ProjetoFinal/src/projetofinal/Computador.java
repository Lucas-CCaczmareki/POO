package projetofinal;

import java.util.Random;

/*
 * Computador implementa runnable, para que oque ele faz possa ser executado em outra thread
 * O Computador representa um jogador controlado pela CPU através de jogadas aleatórias
 */
public class Computador extends Treinador implements Runnable{

    //Atributos
    //Transient indica que esse atributo não deve ser serializado (salvado no arquivo)
    //Isso é feito por que o estado do jogo já tá sendo salvo de outro lugar, não precisamos salvar ele por aqui
    private transient Jogo jogo;

    //Construtor
    public Computador(String nome, Jogo jogo) {
        super(nome);
        this.jogo = jogo;
    }

    //Método que é executado pela thread separada
    @Override
    public void run() {
        try {
            
            System.out.println(getNome() + " está pensando...");
            Thread.sleep(2000);      //sleep pra simular o pensamento do computador
            Random random = new Random();

            Tabuleiro tabuleiro = jogo.getTabuleiro();
            int tamanho = tabuleiro.getTamanhoN();
            int linha, coluna;

            //Escolhe uma célula aleatória até achar uma que não foi revelada
            do {
                linha = random.nextInt(tamanho);
                coluna = random.nextInt(tamanho);
            } while (tabuleiro.getGrade()[linha][coluna].isRevelada());

            System.out.println(getNome() + " escolheu jogar na posição [" + linha + "][" + coluna + "].");
            
            
            jogo.processarJogadaComputador(linha, coluna);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("A thread do computador foi interrompida.");
        }
    }
    
    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }
}