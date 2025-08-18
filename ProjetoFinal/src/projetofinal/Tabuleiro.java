package projetofinal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* Função do Serializable
 *  Serializable permite que os objetos dessa classe podem ser convertidos
 *  em uma sequência de bytes. É isso que permite salvar o estado dos pokémons em um arquivo
 */

 /*
  * Essa classe representa o tabuleiro do jogo. Este é divido em células, onde cada
  * célula pode conter um Pokémon. Ela controla a organização, posicionamento, regras de região
  * e movimentação dos Pokémons no tabuleiro.
  */
public class Tabuleiro implements Serializable {

    //Contém uma matriz simétrica (tamanho N) de células
    private final Celula[][] grade;
    private final int tamanhoN;

    //Constrói as células do tabuleiro
    public Tabuleiro(int tamanhoN) {
        if (tamanhoN % 2 != 0 || tamanhoN <= 0) {
            throw new IllegalArgumentException("O tamanho N do tabuleiro deve ser um número par e positivo.");
        }
        this.tamanhoN = tamanhoN;
        this.grade = new Celula[tamanhoN][tamanhoN];

        //Percorre a matriz de células criando as células
        for (int i = 0; i < tamanhoN; i++) {
            for (int j = 0; j < tamanhoN; j++) {
                grade[i][j] = new Celula(i, j);
            }
        }
    }

    /*
     * Posiciona um pokémon numa célula. Trata de garantir que o pokémon será posicionado
     * numa célula válida (que existe, não está ocupada e não foi alvo de uma jogada)
     */
    public void posicionarPokemon(Pokemon pokemon, int linha, int coluna) throws RegiaoInvalidaException {
        if (linha < 0 || linha >= tamanhoN || coluna < 0 || coluna >= tamanhoN) {
            throw new IndexOutOfBoundsException("Posição [" + linha + "][" + coluna + "] está fora do tabuleiro.");
        }
        if (!this.grade[linha][coluna].estaVazia()) {
            throw new IllegalStateException("A célula [" + linha + "][" + coluna + "] já está ocupada.");
        }

        //Chama o método que valida a região
        //(Cada pokémon da área do seu respectivo tipo)
        validarRegiao(pokemon, linha, coluna);
        this.grade[linha][coluna].setPokemon(pokemon);
    }

    /*
     * Valida a região onde o pokémon vai ser posicionado, garantido que um Pokémon
     * de um tipo seja posicionado apenas na região do seu respectivo tipo
     */
    private void validarRegiao(Pokemon pokemon, int linha, int coluna) throws RegiaoInvalidaException {
        int meio = tamanhoN / 2;
        String tipo = pokemon.getTipo();
        String erroMsg = "A posição [" + linha + "][" + coluna + "] não é válida para um Pokémon do tipo " + tipo + ".";
        
        //Faz as comparações necessárias para descobrir o tipo da célula clicada,
        //e se é o mesmo do pokémon 

        //Superior esquerda
        if (linha < meio && coluna < meio) {
            if (!tipo.equals("Água")) throw new RegiaoInvalidaException(erroMsg);
        
        //Superior direita
        } else if (linha < meio && coluna >= meio) {
            if (!tipo.equals("Floresta")) throw new RegiaoInvalidaException(erroMsg);

        //Inferior esquerda
        } else if (linha >= meio && coluna < meio) {
            if (!tipo.equals("Terra")) throw new RegiaoInvalidaException(erroMsg);

        //Inferior direita
        } else if (linha >= meio && coluna >= meio) {
            if (!tipo.equals("Elétrico")) throw new RegiaoInvalidaException(erroMsg);
        }
    }

    /*
     * Essa função recebe uma linha e uma coluna e retorna se nessa parte do tabuleiro
     * existe ou não um Pokémon.
     */
    public boolean verificarDica(int linha, int coluna) {
        for (int j = 0; j < tamanhoN; j++) {
            if (!grade[linha][j].estaVazia()) return true;
        }
        for (int i = 0; i < tamanhoN; i++) {
            if (!grade[i][coluna].estaVazia()) return true;
        }
        return false;
    }

    /*
     * Serve para encontrar uma célula vizinha para onde um pokémon pode fugir
     */
    public Celula encontrarCelulaParaFuga(Pokemon pokemon, int linha, int coluna) {
        
        List<Celula> vizinhos = new ArrayList<>();

        //Percorre um quadrado
        /*
         * -> -> ->
         * -> -> ->
         * -> -> ->
         */
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; //ignora a própria célula

                //Define as coordenadas da célula
                int novaLinha = linha + i;
                int novaColuna = coluna + j;

                //Testa se da dentro dos limites do tabuleiro e adiciona no array de posições
                if (novaLinha >= 0 && novaLinha < tamanhoN && novaColuna >= 0 && novaColuna < tamanhoN) {
                    vizinhos.add(grade[novaLinha][novaColuna]);
                }
            }
        }
        
        //Embaralha o array pra gerar aleatoriedade
        Collections.shuffle(vizinhos);
        
        //Pra cada célula no array
        for (Celula vizinho : vizinhos) {
            
            //Testa se está vazia e se já foi relevada
            if (vizinho.estaVazia() && !vizinho.isRevelada()) {
                try {
                    //Valida a região pra um pokémon não se mover pra fora do seu Habitat
                    validarRegiao(pokemon, vizinho.getLinha(), vizinho.getColuna());
                    return vizinho; //retorna a célula pra onde o pkm se moveu
                } catch (RegiaoInvalidaException e) {
                   //Se for uma região inválida, segue o loop
                }
            }
        }

        //Se não encontrou uma célula pra se mover retorna null
        return null; 
    }

    /* 
    *   Recebe a coordenada de uma célula e retorna a região dela
    */
    public String getTipoRegiao(int linha, int coluna) {
        int meio = tamanhoN / 2;
        if (linha < meio && coluna < meio) return "Água";
        if (linha < meio && coluna >= meio) return "Floresta";
        if (linha >= meio && coluna < meio) return "Terra";
        return "Elétrico";
    }

    /*
     * Remove um pokémon de uma das células deixando-a vazia
     */
    public void removerPokemon(int linha, int coluna) {
        grade[linha][coluna].setPokemon(null);
    }
    
    /*
     * Percorre todo o tabuleiro em busca de um pokémon selvagem
     * Esse método serve para conferir se algum dos jogadores já ganhou o jogo
     */
    public boolean temPokemonSelvagem() {
        //Percorre todo o tabuleiro
        for (int i = 0; i < tamanhoN; i++) {
            for (int j = 0; j < tamanhoN; j++) {
                //Essa condição dupla garante que não vai acessar um pokémon que não existe
                if (grade[i][j].getPokemon() != null && grade[i][j].getPokemon().isSelvagem()) {
                    return true;
                }
            }
        }
        return false;
    }

    //Getters e setters
    public Celula[][] getGrade() { return grade; }
    public int getTamanhoN() { return tamanhoN; }
}