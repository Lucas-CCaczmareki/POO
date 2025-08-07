package ex10.pkg2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConversorTemperatura extends JFrame {
    //Declaramos os componentes aqui, pra acessar eles em toda classe
    private JTextField campoCelsius;
    private JButton botaoConverter;
    private JLabel labelResultado;

    //Construtor que cria a janela e tudo
    public ConversorTemperatura() {
        setTitle("Conversor de Temperatura");
        setSize(500,500);

        //Faz a janela aparecer no centro e fechar com o X
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(3, 1, 10, 10));

        //Criação e configuração dos componentes

        //Painel 1: rótulo e campo de entrada
        JPanel painelEntrada = new JPanel(new FlowLayout());
        JLabel labelInstrucao = new JLabel("Temperatura em Celsius: ");

        campoCelsius = new JTextField(10); //Cria um campo de texto com largura pra +- 10 caracteres

        painelEntrada.add(labelInstrucao);
        painelEntrada.add(campoCelsius);

        //Painel 2: botão
        JPanel painelBotao = new JPanel(new FlowLayout());
        botaoConverter = new JButton("Converter para Fahrenheit");
        painelBotao.add(botaoConverter);

        //Painel 3: pra resultado
        JPanel painelResultado = new JPanel(new FlowLayout());
        labelResultado = new JLabel("Resultado aparecerá aqui.");
        labelResultado.setFont(new Font("Monocraft", Font.BOLD, 16));
        painelResultado.add(labelResultado);

        //Lógica do botão
        //Cria o listener que reage ao botão
        botaoConverter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Pega o texto do campo de entrada
                String textoCelsius = campoCelsius.getText();

                try {
                    //Tenta converter pra double
                    double celsius = Double.parseDouble(textoCelsius);

                    //Faz o cálculo da conversão
                    double fahrenheit = (celsius * 9.0 / 5.0) + 32;

                    //Coloca o resultado no label de saída
                    labelResultado.setText(String.format("%.2f °F", fahrenheit));
                } catch (NumberFormatException ex) {
                    //Se a conversão falhar (usuário fez merda), executa o catch
                    JOptionPane.showMessageDialog(
                        ConversorTemperatura.this, //q porra é essa???
                        "Erro: Por favor, digite um número válido.",
                        "Erro de entrada",
                        JOptionPane.ERROR_MESSAGE
                    );

                    //Limpa os campos pra uma nova tentativa
                    labelResultado.setText("Entrada inválida");
                    campoCelsius.setText("");
                }
            }
        });

        //Adiciona os painéis à Janela
        add(painelEntrada);
        add(painelBotao);
        add(painelResultado);

    }
}
