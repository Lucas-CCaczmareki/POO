package projetofinal;

import java.io.*;

/**
 * Classe responsável por lidar com a leitura e escrita de arquivos
 * [cite_start]para salvar e carregar o estado do jogo. [cite: 148]
 */
public class GerenciadorDeArquivos {

    /**
     * Salva o estado atual do jogo em um arquivo.
     * @param jogo O objeto Jogo a ser salvo.
     * @param caminhoArquivo O caminho do arquivo onde o jogo será salvo.
     */
    public void salvarJogo(Jogo jogo, String caminhoArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            oos.writeObject(jogo);
        }
    }

    /**
     * Carrega um estado de jogo de um arquivo.
     * @param caminhoArquivo O caminho do arquivo a ser carregado.
     * @return O objeto Jogo carregado.
     */
    public Jogo carregarJogo(String caminhoArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoArquivo))) {
            return (Jogo) ois.readObject();
        }
    }
}
