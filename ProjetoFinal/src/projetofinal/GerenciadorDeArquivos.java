package projetofinal;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * INFORMAÇÕES IMPORTANTES: Leitura/Escrita de Arquivos
 * Gerencia a persistência de dados do jogo. Utiliza a serialização de objetos
 * para salvar e carregar o estado completo de uma partida, tratando as exceções
 * que podem ocorrer durante o processo.
 */
public class GerenciadorDeArquivos {

    /**
     * Salva o estado atual do objeto Jogo em um arquivo.
     * Abre uma janela para o usuário escolher onde salvar o arquivo.
     * @param parent O componente JFrame pai (a janela do jogo) para centralizar o diálogo.
     * @param jogo O objeto Jogo que contém o estado atual da partida a ser salvo.
     */
    public void salvarJogo(JFrame parent, Jogo jogo) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar Jogo Pokémon");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Jogo Pokémon Salvo (*.sav)", "sav"));

        int userSelection = fileChooser.showSaveDialog(parent);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File arquivoParaSalvar = fileChooser.getSelectedFile();
            String caminho = arquivoParaSalvar.getAbsolutePath();
            if (!caminho.toLowerCase().endsWith(".sav")) {
                arquivoParaSalvar = new File(caminho + ".sav");
            }

            // Usa try-with-resources para garantir que o stream seja fechado automaticamente
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoParaSalvar))) {
                oos.writeObject(jogo);
                JOptionPane.showMessageDialog(parent, "Jogo salvo com sucesso!", "Salvar Jogo", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                // INFORMAÇÕES IMPORTANTES: Exceções devem ser tratadas
                JOptionPane.showMessageDialog(parent, "Ocorreu um erro ao salvar o jogo:\n" + e.getMessage(), "Erro de Salvamento", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    /**
     * Carrega um estado de jogo de um arquivo.
     * Abre uma janela para o usuário escolher qual arquivo carregar.
     * @param parent O componente JFrame pai (a janela de boas-vindas) para centralizar o diálogo.
     * @return O objeto Jogo carregado do arquivo, ou null se a operação for cancelada ou falhar.
     */
    public Jogo carregarJogo(JFrame parent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Carregar Jogo Pokémon");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Jogo Pokémon Salvo (*.sav)", "sav"));

        int userSelection = fileChooser.showOpenDialog(parent);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File arquivoParaCarregar = fileChooser.getSelectedFile();

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoParaCarregar))) {
                Jogo jogoCarregado = (Jogo) ois.readObject();
                JOptionPane.showMessageDialog(parent, "Jogo carregado com sucesso!", "Carregar Jogo", JOptionPane.INFORMATION_MESSAGE);
                return jogoCarregado;
            } catch (IOException | ClassNotFoundException e) {
                // INFORMAÇÕES IMPORTANTES: Exceções devem ser tratadas
                JOptionPane.showMessageDialog(parent, "Ocorreu um erro ao carregar o jogo:\n" + e.getMessage(), "Erro de Carregamento", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                return null;
            }
        }
        return null; // Retorna null se o usuário cancelar a seleção do arquivo
    }
}