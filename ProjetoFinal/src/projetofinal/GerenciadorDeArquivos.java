package projetofinal;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

/*
 * Classe responsável por todas operações envolvendo arquivos
 * - salvar
 * - carregar
 * - registrar o histórico de partidas
 */
public class GerenciadorDeArquivos {

    /**
     * Salva o estado atual do objeto Jogo em um arquivo.
     * @param parent O JFrame pai, para centralizar a janela de diálogo.
     * @param jogo O objeto Jogo a ser salvo.
     */

    public void salvarJogo(JFrame parent, Jogo jogo) {
        JFileChooser fileChooser = new JFileChooser();

        //Seta a opção que vai criar o arquivo
        fileChooser.setDialogTitle("Salvar Jogo");

        //Seta um filtro, pra só aparecer arquivos com a extensão .sav
        fileChooser.setFileFilter(new FileNameExtensionFilter("Jogo Pokémon (*.sav)", "sav"));

        if (fileChooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            String caminho = arquivo.getAbsolutePath();

            //confere se é um arquivo válido
            if (!caminho.toLowerCase().endsWith(".sav")) {
                arquivo = new File(caminho + ".sav");
            }

            //Tenta serializar o objeto java e gravar num arquivo
            //O try-with-resources aqui garante que o arquivo vai ser fechado mesmo se der erro
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

            //Tenta abrir o arquivo serializado e carregar pra dentro de um objeto jogo
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
        return null; 
    }

    /**
     * Salva o resultado final de uma partida em um arquivo de texto 'historico.txt'.
     */
    public void salvarHistorico(String jogador1, int pontuacao1, String jogador2, int pontuacao2, String vencedor) {
        // O 'true' no FileWriter indica que o conteúdo será adicionado ao final do arquivo (append).
        try (FileWriter fw = new FileWriter("historico.txt", true);
            BufferedWriter bw = new BufferedWriter(fw); //melhora a performance de escrita
            PrintWriter out = new PrintWriter(bw)) {    //facilita a escrita de linhas de texto
            
            //Escreve os bagui no arquivo
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