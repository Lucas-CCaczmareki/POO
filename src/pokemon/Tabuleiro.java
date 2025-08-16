package pokemon;

import java.io.Serializable;
import java.util.Random;

public class Tabuleiro implements Serializable {
    private static final int TAMANHO = 8;
    private Celula[][] grade;
    private Random random;
    
    public Tabuleiro() {
        this.grade = new Celula[TAMANHO][TAMANHO];
        this.random = new Random();
        
        // Inicializar todas as células
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                grade[i][j] = new Celula(i, j);
            }
        }
    }
    
    public void posicionarPokemon(Pokemon pokemon, int linha, int coluna) throws RegiaoInvalidaException {
        if (!isRegiaoValida(pokemon.getTipo(), linha, coluna)) {
            throw new RegiaoInvalidaException("Pokémon " + pokemon.getNome() + " não pode ser posicionado nesta região!");
        }
        
        if (grade[linha][coluna].getPokemon() != null) {
            throw new RegiaoInvalidaException("Posição já ocupada!");
        }
        
        grade[linha][coluna].setPokemon(pokemon);
    }
    
    public void posicionarPokemonAleatorio(Pokemon pokemon) {
        int linha, coluna;
        int tentativas = 0;
        boolean posicionado = false;
        
        while (!posicionado && tentativas < 200) {
            linha = random.nextInt(TAMANHO);
            coluna = random.nextInt(TAMANHO);
            tentativas++;
            
            if (isRegiaoValida(pokemon.getTipo(), linha, coluna) && grade[linha][coluna].getPokemon() == null) {
                grade[linha][coluna].setPokemon(pokemon);
                posicionado = true;
                System.out.println("Pokémon " + pokemon.getNome() + " posicionado em (" + linha + "," + coluna + ")");
            }
        }
        
        if (!posicionado) {
            System.out.println("ERRO: Não foi possível posicionar " + pokemon.getNome());
        }
    }
    
    public boolean isRegiaoValida(String tipo, int linha, int coluna) {
        switch (tipo) {
            case "Agua":
                return linha < TAMANHO/2 && coluna < TAMANHO/2;
            case "Floresta":
                return linha < TAMANHO/2 && coluna >= TAMANHO/2;
            case "Terra":
                return linha >= TAMANHO/2 && coluna < TAMANHO/2;
            case "Eletrico":
                return linha >= TAMANHO/2 && coluna >= TAMANHO/2;
            default:
                return false;
        }
    }
    
    public Pokemon getPokemon(int linha, int coluna) {
        return grade[linha][coluna].getPokemon();
    }
    
    public Celula getCelula(int linha, int coluna) {
        return grade[linha][coluna];
    }
    
    public void removerPokemon(int linha, int coluna) {
        grade[linha][coluna].setPokemon(null);
    }
    
    public void moverPokemon(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        if (grade[linhaOrigem][colunaOrigem].getPokemon() != null && grade[linhaDestino][colunaDestino].getPokemon() == null) {
            grade[linhaDestino][colunaDestino].setPokemon(grade[linhaOrigem][colunaOrigem].getPokemon());
            grade[linhaOrigem][colunaOrigem].setPokemon(null);
        }
    }
    
    public int getTamanho() {
        return TAMANHO;
    }
    
    public boolean temPokemonSelvagem() {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                if (grade[i][j].getPokemon() != null && grade[i][j].getPokemon().isSelvagem()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public int contarPokemonsSelvagens() {
        int count = 0;
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                if (grade[i][j].getPokemon() != null && grade[i][j].getPokemon().isSelvagem()) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public int contarPokemons() {
        int count = 0;
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                if (grade[i][j].getPokemon() != null) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public Celula[][] getGrade() {
        return grade;
    }
}
