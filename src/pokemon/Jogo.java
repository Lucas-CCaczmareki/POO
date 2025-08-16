package pokemon;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Jogo implements Serializable {
    private Tabuleiro tabuleiro;
    private Treinador jogador;
    private Treinador computador;
    private boolean jogoAtivo;
    private ExecutorService executor;
    private Random random;
    private int turno;
    
    public Jogo() {
        this.tabuleiro = new Tabuleiro();
        this.jogador = new Treinador("Jogador");
        this.computador = new Treinador("Computador");
        this.jogoAtivo = false;
        this.executor = Executors.newSingleThreadExecutor();
        this.random = new Random();
        this.turno = 0;
    }
    
    public void iniciarJogo() {
        jogoAtivo = true;
        turno = 0;
        System.out.println("Jogo iniciado!");
    }
    
    public void fazerJogadaJogador(int linha, int coluna) {
        if (!jogoAtivo) return;
        
        Celula celula = tabuleiro.getCelula(linha, coluna);
        if (celula.isRevelada()) return; // Célula já foi clicada
        
        celula.setRevelada(true);
        celula.setReveladaPeloJogador(true);
        
        Pokemon pokemonEncontrado = celula.getPokemon();
        
        if (pokemonEncontrado == null) {
            System.out.println("Nada encontrado nesta posição!");
        } else if (pokemonEncontrado.isSelvagem()) {
            tentarCapturar(pokemonEncontrado, linha, coluna);
        } else {
            iniciarBatalha(jogador, computador, pokemonEncontrado);
        }
        
        turno++;
        PokemonTerra.incrementarTurno();
        
        // Computador faz jogada em paralelo
        executor.submit(() -> fazerJogadaComputador());
        
        // Verificar fim do jogo APÓS a jogada
        if (verificarFimJogo()) {
            System.out.println("Jogo terminou! Todos os Pokémons selvagens foram capturados.");
        }
    }
    
    private void fazerJogadaComputador() {
        try {
            Thread.sleep(1000); // Simula "tempo de pensar"
            
            if (!jogoAtivo) return;
            
            // Escolhe posição aleatória
            int linha = random.nextInt(tabuleiro.getTamanho());
            int coluna = random.nextInt(tabuleiro.getTamanho());
            
            Pokemon pokemonEncontrado = tabuleiro.getPokemon(linha, coluna);
            
            if (pokemonEncontrado != null && pokemonEncontrado.isSelvagem()) {
                tentarCapturar(pokemonEncontrado, linha, coluna);
            }
            
            System.out.println("Computador fez sua jogada!");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private void tentarCapturar(Pokemon pokemon, int linha, int coluna) {
        // Lançamento da Pokébola (50% de chance)
        boolean capturaSucesso = random.nextBoolean();
        
        if (capturaSucesso) {
            // Captura bem-sucedida
            if (turno % 2 == 0) {
                jogador.capturarPokemon(pokemon);
            } else {
                computador.capturarPokemon(pokemon);
            }
            tabuleiro.removerPokemon(linha, coluna);
            System.out.println("Pokémon " + pokemon.getNome() + " capturado com sucesso!");
        } else {
            // Captura falhou - Pokémon foge para posição aleatória
            tabuleiro.removerPokemon(linha, coluna);
            tabuleiro.posicionarPokemonAleatorio(pokemon);
            System.out.println(pokemon.getNome() + " fugiu da Pokébola!");
        }
    }
    
    private void iniciarBatalha(Treinador atacante, Treinador defensor, Pokemon pokemonEncontrado) {
        Pokemon pokemonAtacante = atacante.getPokemonPrincipal();
        Pokemon pokemonDefensor = defensor.getPokemonPrincipal();
        
        if (pokemonAtacante == null || pokemonDefensor == null) return;
        
        System.out.println("Batalha iniciada: " + pokemonAtacante.getNome() + " vs " + pokemonDefensor.getNome());
        
        while (pokemonAtacante.getEnergia() > 0 && pokemonDefensor.getEnergia() > 0) {
            // Ataque do atacante
            int dano = pokemonAtacante.calcularDano();
            pokemonDefensor.receberDano(dano);
            System.out.println(pokemonAtacante.getNome() + " causou " + dano + " de dano!");
            
            if (pokemonDefensor.getEnergia() <= 0) break;
            
            // Ataque do defensor
            dano = pokemonDefensor.calcularDano();
            pokemonAtacante.receberDano(dano);
            System.out.println(pokemonDefensor.getNome() + " causou " + dano + " de dano!");
        }
        
        // Determina vencedor
        Pokemon vencedor = pokemonAtacante.getEnergia() > 0 ? pokemonAtacante : pokemonDefensor;
        Treinador treinadorVencedor = pokemonAtacante.getEnergia() > 0 ? atacante : defensor;
        
        vencedor.aumentarExperiencia(50);
        treinadorVencedor.atualizarPontuacao();
        
        // Restaura energia
        pokemonAtacante.restaurarEnergia();
        pokemonDefensor.restaurarEnergia();
        
        System.out.println(vencedor.getNome() + " venceu a batalha!");
    }
    
    public boolean verificarFimJogo() {
        // Só termina se não há mais Pokémons selvagens E ambos os treinadores têm Pokémons
        if (!tabuleiro.temPokemonSelvagem() && 
            jogador.getTime().size() > 0 && 
            computador.getTime().size() > 0) {
            jogoAtivo = false;
            return true;
        }
        return false;
    }
    
    public Treinador getVencedor() {
        if (jogador.getPontuacao() > computador.getPontuacao()) {
            return jogador;
        } else if (computador.getPontuacao() > jogador.getPontuacao()) {
            return computador;
        }
        return null; // Empate
    }
    
    // Getters
    public Tabuleiro getTabuleiro() { return tabuleiro; }
    public Treinador getJogador() { return jogador; }
    public Treinador getComputador() { return computador; }
    public boolean isJogoAtivo() { return jogoAtivo; }
    public int getTurno() { return turno; }
    
    public void toggleModoDebug() {
        // Implementação do debug mode
        for (int i = 0; i < tabuleiro.getTamanho(); i++) {
            for (int j = 0; j < tabuleiro.getTamanho(); j++) {
                Celula celula = tabuleiro.getCelula(i, j);
                if (!celula.isReveladaPeloJogador()) {
                    // Se não foi revelada pelo jogador, alterna o estado de revelação
                    celula.setRevelada(!celula.isRevelada());
                }
            }
        }
    }
    
    public void encerrarJogo() {
        jogoAtivo = false;
        executor.shutdown();
    }
}
