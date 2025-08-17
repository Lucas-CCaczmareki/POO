package pokemon;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

/**
 * Gerencia a leitura e escrita de arquivos para persistência de dados.
 * Esta versão foi corrigida para integrar a interface gráfica (JFileChooser)
 * e fornecer feedback ao usuário (JOptionPane).
 */
public class GerenciadorDeArquivos {

    /**
     * Salva o estado atual do objeto Jogo em um arquivo.
     * @param parent O JFrame pai, para centralizar a janela de diálogo.
     * @param jogo O objeto Jogo a ser salvo.
     */
    public void salvarJogo(JFrame parent, Jogo jogo) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar Jogo");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Jogo Pokémon (*.sav)", "sav"));

        if (fileChooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            String caminho = arquivo.getAbsolutePath();
            if (!caminho.toLowerCase().endsWith(".sav")) {
                arquivo = new File(caminho + ".sav");
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
                oos.writeObject(jogo);
                JOptionPane.showMessageDialog(parent, "Jogo salvo com sucesso!", "Salvar Jogo", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(parent, "Erro ao salvar o jogo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    /**
     * Carrega um estado de jogo de um arquivo.
     * @param parent O JFrame pai, para centralizar a janela de diálogo.
     * @return O objeto Jogo carregado, ou null se a operação falhar ou for cancelada.
     */
    public Jogo carregarJogo(JFrame parent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Carregar Jogo");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Jogo Pokémon (*.sav)", "sav"));

        if (fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                Jogo jogoCarregado = (Jogo) ois.readObject();
                JOptionPane.showMessageDialog(parent, "Jogo carregado com sucesso!", "Carregar Jogo", JOptionPane.INFORMATION_MESSAGE);
                return jogoCarregado;
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(parent, "Erro ao carregar o jogo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                return null;
            }
        }
        return null; // Usuário cancelou
    }

    /**
     * Salva o resultado final de uma partida em um arquivo de texto 'historico.txt'.
     */
    public void salvarHistorico(String jogador1, int pontuacao1, String jogador2, int pontuacao2, String vencedor) {
        // O 'true' no FileWriter indica que o conteúdo será adicionado ao final do arquivo (append).
        try (FileWriter fw = new FileWriter("historico.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            
            out.println("Data: " + java.time.LocalDateTime.now());
            out.println(jogador1 + " - Pontuação: " + pontuacao1);
            out.println(jogador2 + " - Pontuação: " + pontuacao2);
            out.println("Vencedor: " + vencedor);
            out.println("----------------------------------------");
            
        } catch (IOException e) {
            System.err.println("Erro ao salvar histórico: " + e.getMessage());
        }
    }
}