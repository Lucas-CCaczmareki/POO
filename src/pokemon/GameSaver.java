package pokemon;

import java.io.*;
import java.util.List;

public class GameSaver {
    
    public static void salvarJogo(Jogo jogo, String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(jogo);
        }
    }
    
    public static Jogo carregarJogo(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return (Jogo) ois.readObject();
        }
    }
    
    public static void salvarHistorico(String jogador1, String jogador2, int pontuacao1, int pontuacao2, String vencedor) {
        try (FileWriter fw = new FileWriter("historico.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            
            out.println("Data: " + java.time.LocalDateTime.now());
            out.println("Jogador 1: " + jogador1 + " - Pontuação: " + pontuacao1);
            out.println("Jogador 2: " + jogador2 + " - Pontuação: " + pontuacao2);
            out.println("Vencedor: " + vencedor);
            out.println("----------------------------------------");
            
        } catch (IOException e) {
            System.err.println("Erro ao salvar histórico: " + e.getMessage());
        }
    }
}
