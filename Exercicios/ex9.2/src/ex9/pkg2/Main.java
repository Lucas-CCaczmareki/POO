package ex9.pkg2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        lerArquivoSimulado();
    }
    
    //Static faz com que esse método pertença diretamente à classe (e não à um objeto da classe instanciado)
    //Ou seja, é obrigatório para tudo funcionar nesse exemplo
    public static void lerArquivoSimulado() {
        BufferedReader reader = null;

        System.out.println("Tentando abrir o arquivo... ");

        try {
            //Tenta criar um leitor pra um arquivo que não existe
            //Ou seja, vai falhar, ai a g ente uma IOException e pega.
            reader = new BufferedReader(new FileReader("arq.txt"));

            //Esse código nunca vai ser executado por que o arquivo não existe, mas se existisse ele seria
            System.out.println("Arquivo aberto com sucesso!");
            String linha = reader.readLine();
            System.out.println("Primeira linha: " + linha);


        //IOException é uma classe que representar os erros relacionados à input/output
        //e é uma variável dessa classe.
        } catch (IOException e) {
            //Como o arquivo não existe, o erro é capturado aqui.
            System.out.println("ERRO: Falha ao tentar ler. Motivo: " + e.getMessage());

        } finally {
            //Capturando o não o erro, executa esse bloco
            System.out.println("Operação finalizada");

            //Fecha o leitor
            if (reader != null) {
                try {
                    reader.close();
                    System.out.println("Leitura encerrada.");
                } catch (IOException e) {
                    System.out.println("Falha ao fechar o leitor.");
                }
            }
        }
    }
}
