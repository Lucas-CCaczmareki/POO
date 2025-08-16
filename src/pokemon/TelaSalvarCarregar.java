package pokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class TelaSalvarCarregar extends JDialog {
    private Jogo jogo;
    private JTextField txtNomeArquivo;
    private JButton btnSalvar;
    private JButton btnCarregar;
    private JButton btnCancelar;
    
    public TelaSalvarCarregar(JFrame parent, Jogo jogo) {
        super(parent, "Salvar/Carregar Jogo", true);
        this.jogo = jogo;
        
        configurarInterface();
    }
    
    private void configurarInterface() {
        setLayout(new BorderLayout());
        setSize(400, 200);
        setLocationRelativeTo(getOwner());
        
        // Painel principal
        JPanel painelPrincipal = new JPanel(new GridLayout(3, 1, 10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Campo nome do arquivo
        JPanel painelNome = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblNome = new JLabel("Nome do arquivo:");
        txtNomeArquivo = new JTextField(20);
        txtNomeArquivo.setText("jogo_salvo.dat");
        painelNome.add(lblNome);
        painelNome.add(txtNomeArquivo);
        
        // Botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnSalvar = new JButton("Salvar Jogo");
        btnCarregar = new JButton("Carregar Jogo");
        btnCancelar = new JButton("Cancelar");
        
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarJogo();
            }
        });
        
        btnCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarJogo();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCarregar);
        painelBotoes.add(btnCancelar);
        
        // Adicionar componentes
        painelPrincipal.add(painelNome);
        painelPrincipal.add(new JLabel("Escolha uma opção:"));
        painelPrincipal.add(painelBotoes);
        
        add(painelPrincipal, BorderLayout.CENTER);
    }
    
    private void salvarJogo() {
        String nomeArquivo = txtNomeArquivo.getText().trim();
        if (nomeArquivo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite um nome para o arquivo!", 
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            GameSaver.salvarJogo(jogo, nomeArquivo);
            JOptionPane.showMessageDialog(this, "Jogo salvo com sucesso!", 
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar jogo: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void carregarJogo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "Arquivos de jogo (*.dat)", "dat"));
        
        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            try {
                Jogo jogoCarregado = GameSaver.carregarJogo(arquivo.getAbsolutePath());
                // Aqui você pode implementar a lógica para substituir o jogo atual
                JOptionPane.showMessageDialog(this, "Jogo carregado com sucesso!", 
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar jogo: " + e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
