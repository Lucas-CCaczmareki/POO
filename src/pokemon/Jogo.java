package pokemon;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Versão final da classe Jogo, agora utilizando a PokemonFactory para
 * configurar a partida com todos os Pokémon disponíveis.
 */
public class Jogo implements Serializable {

    private Tabuleiro tabuleiro;
    private Treinador jogador;
    private Computador computador;
    private boolean turnoDoJogador;
    private int numeroDoTurno;
    private int dicasRestantes;
    private int pokemonsSelvagensRestantes;
    private boolean jogoTerminou;
    private transient List<ObservadorJogo> observadores;
    private boolean modoDebugAtivo;
    private boolean emBatalha = false;
    private Pokemon pokemonJogadorBatalha;
    private Pokemon pokemonOponenteBatalha;
    private Treinador atacanteOriginal;
    private Treinador defensorOriginal;
    private Celula celulaBatalha;

    public Jogo() {
        this.observadores = new ArrayList<>();
        this.tabuleiro = new Tabuleiro(8);
        this.jogador = new Treinador("Jogador 1");
        this.computador = new Computador("Computador", this);
        this.turnoDoJogador = true;
        this.numeroDoTurno = 1;
        this.dicasRestantes = 3;
        this.jogoTerminou = false;
        this.modoDebugAtivo = false;
        

        configuracaoInicialAleatoria();
    }

    /**
     * CORRIGIDO: Agora usa a PokemonFactory para popular o jogo com todos
     * os Pokémon disponíveis de forma aleatória.
     */
    private void configuracaoInicialAleatoria() {
        pokemonsSelvagensRestantes = 0;

        // 1. Pega todos os nomes de Pokémon da Factory e embaralha
        List<String> nomesDisponiveis = new ArrayList<>(Arrays.asList(PokemonFactory.getPokemonsDisponiveis()));
        Collections.shuffle(nomesDisponiveis);

        // 2. Associa os dois primeiros da lista embaralhada aos treinadores
        String nomePkmJogador = nomesDisponiveis.remove(0);
        Pokemon pJogador = PokemonFactory.criarPokemon(nomePkmJogador);
        jogador.adicionarPokemonInicial(pJogador);
        posicionarAleatoriamente(pJogador);

        String nomePkmComputador = nomesDisponiveis.remove(0);
        Pokemon pComputador = PokemonFactory.criarPokemon(nomePkmComputador);
        computador.adicionarPokemonInicial(pComputador);
        posicionarAleatoriamente(pComputador);
        
        // 3. O restante da lista são os Pokémon selvagens
        for (String nomeSelvagem : nomesDisponiveis) {
            Pokemon pSelvagem = PokemonFactory.criarPokemon(nomeSelvagem);
            posicionarAleatoriamente(pSelvagem);
            pokemonsSelvagensRestantes++;
        }
    }

    private void posicionarAleatoriamente(Pokemon pokemon) {
        while (true) {
            try {
                int linha = new Random().nextInt(tabuleiro.getTamanhoN());
                int coluna = new Random().nextInt(tabuleiro.getTamanhoN());
                if (tabuleiro.getGrade()[linha][coluna].estaVazia()) {
                    tabuleiro.posicionarPokemon(pokemon, linha, coluna);
                    break;
                }
            } catch (RegiaoInvalidaException | IllegalStateException e) { /* Tenta de novo */ }
        }
    }

    public void processarJogadaJogador(int linha, int coluna) {
        if (!turnoDoJogador || jogoTerminou || emBatalha) return;
        processarLogicaDaCelula(jogador, linha, coluna);
        if (!emBatalha) {
            passarTurnoParaComputador();
        }
    }

    public void processarJogadaComputador(int linha, int coluna) {
        processarLogicaDaCelula(computador, linha, coluna);
        if (!emBatalha) {
            this.turnoDoJogador = true;
            notificarObservadores("JOGADA_CONCLUIDA", null);
        }
    }
    
    private void processarLogicaDaCelula(Treinador treinadorAtivo, int linha, int coluna) {
        Celula celula = tabuleiro.getGrade()[linha][coluna];
        if (celula.isRevelada()) return;
        celula.setRevelada(true);

        Pokemon pEncontrado = celula.getPokemon();

        if (pEncontrado != null) {
            if (pEncontrado.isSelvagem()) {
                iniciarCaptura(treinadorAtivo, pEncontrado, celula);
            } else if (treinadorAtivo.getPokemonPrincipal() != pEncontrado) {
                Treinador oponente = (treinadorAtivo == jogador) ? computador : jogador;
                this.celulaBatalha = celula; // Armazena a célula da batalha
                prepararParaBatalha(treinadorAtivo, oponente);
            }
        }
    }
    
    private void passarTurnoParaComputador() {
        if (emBatalha) return;
        this.turnoDoJogador = false;
        notificarObservadores("JOGADA_CONCLUIDA", null);
        new Thread(computador).start();
    }
    
    public void prepararParaBatalha(Treinador atacante, Treinador defensor) {
        this.emBatalha = true;
        this.atacanteOriginal = atacante;
        this.defensorOriginal = defensor;
        this.pokemonJogadorBatalha = atacante.getPokemonPrincipal();
        this.pokemonOponenteBatalha = defensor.getPokemonPrincipal();
        notificarObservadores("BATALHA_INICIADA", null);
    }
    
    public void executarTurnoBatalha() {
        if (!emBatalha) return;
        
        StringBuilder logDoTurno = new StringBuilder();
        int turnoBatalha = this.numeroDoTurno;
        String tipoRegiao = tabuleiro.getTipoRegiao(celulaBatalha.getLinha(), celulaBatalha.getColuna());
        
        // --- Turno do Jogador ---
        int danoCausado = pokemonJogadorBatalha.calcularDano(turnoBatalha);

        // Lógica da habilidade de Terra
        if (pokemonJogadorBatalha instanceof PokemonTerra && turnoBatalha % 2 != 0) {
            logDoTurno.append(pokemonJogadorBatalha.getNome()).append(" usa sua fúria em um turno ímpar!\n");
        }
        // Lógica da habilidade de Floresta
        if (pokemonJogadorBatalha instanceof PokemonFloresta) {
            logDoTurno.append(pokemonJogadorBatalha.getNome()).append(" se regenerou ao atacar!\n");
        }
        
        // Lógica da habilidade de Água (defensiva)
        if (pokemonOponenteBatalha instanceof PokemonAgua && !tipoRegiao.equals("Água")) {
            danoCausado = (int) (danoCausado * 0.8);
            logDoTurno.append(pokemonOponenteBatalha.getNome()).append(" resistiu ao ataque em ambiente adverso!\n");
        }
        
        pokemonOponenteBatalha.receberDano(danoCausado);
        logDoTurno.append(pokemonJogadorBatalha.getNome()).append(" causou ").append(danoCausado).append(" de dano!\n");

        if (pokemonOponenteBatalha.getEnergia() <= 0) {
            encerrarBatalha(pokemonJogadorBatalha, atacanteOriginal);
            return;
        }

        // --- Turno do Oponente ---
        int danoRecebido = pokemonOponenteBatalha.calcularDano(turnoBatalha);
        // ... (lógica similar para as habilidades do oponente) ...
        pokemonJogadorBatalha.receberDano(danoRecebido);
        logDoTurno.append(pokemonOponenteBatalha.getNome()).append(" causou ").append(danoRecebido).append(" de dano!\n");

        if (pokemonJogadorBatalha.getEnergia() <= 0) {
            encerrarBatalha(pokemonOponenteBatalha, defensorOriginal);
            return;
        }

        this.numeroDoTurno++;
        notificarObservadores("BATALHA_ATUALIZADA", logDoTurno.toString());
    }
    /**
     * NOVO: Método para processar a troca de Pokémon durante a batalha.
     * @param indiceNaMochila O índice do Pokémon escolhido na mochila do jogador.
     */
    public void executarTrocaPokemonBatalha(int indiceNaMochila) {
        if (!emBatalha) return;

        StringBuilder logDoTurno = new StringBuilder();

        // 1. Jogador troca de Pokémon
        atacanteOriginal.trocarPokemonPrincipal(indiceNaMochila);
        this.pokemonJogadorBatalha = atacanteOriginal.getPokemonPrincipal(); // Atualiza a referência
        logDoTurno.append(atacanteOriginal.getNome()).append(" trocou para ").append(pokemonJogadorBatalha.getNome()).append("!\n");

        // 2. Oponente ataca, pois a troca gasta o turno
        int danoRecebido = pokemonOponenteBatalha.calcularDano(numeroDoTurno);
        pokemonJogadorBatalha.receberDano(danoRecebido);
        logDoTurno.append(pokemonOponenteBatalha.getNome()).append(" aproveitou a troca e causou ").append(danoRecebido).append(" de dano!\n");

        if (pokemonJogadorBatalha.getEnergia() <= 0) {
            encerrarBatalha(pokemonOponenteBatalha, defensorOriginal);
            return;
        }
        
        this.numeroDoTurno++;
        notificarObservadores("BATALHA_ATUALIZADA", logDoTurno.toString());
    }

    public void executarFugaBatalha() {
        this.emBatalha = false;
        pokemonJogadorBatalha.restaurarEnergia();
        pokemonOponenteBatalha.restaurarEnergia();
        notificarObservadores("BATALHA_TERMINADA", atacanteOriginal.getNome() + " fugiu da batalha!");
        if (atacanteOriginal == jogador) {
             passarTurnoParaComputador();
        } else {
            this.turnoDoJogador = true;
            notificarObservadores("JOGADA_CONCLUIDA", null);
        }
    }

    private void encerrarBatalha(Pokemon vencedor, Treinador treinadorVencedor) {
        this.emBatalha = false;
        pokemonJogadorBatalha.restaurarEnergia();
        pokemonOponenteBatalha.restaurarEnergia();
        vencedor.aumentarPontosDeExperiencia(50);
        notificarObservadores("BATALHA_TERMINADA", vencedor.getNome() + " venceu a batalha!");
        if (atacanteOriginal == jogador) {
             passarTurnoParaComputador();
        } else {
            this.turnoDoJogador = true;
            notificarObservadores("JOGADA_CONCLUIDA", null);
        }
    }

    private void iniciarCaptura(Treinador treinador, Pokemon pokemonSelvagem, Celula celulaOriginal) {
        if (new Random().nextBoolean()) {
            treinador.capturarPokemon(pokemonSelvagem);
            celulaOriginal.setPokemon(null);
            pokemonsSelvagensRestantes--;
            notificarObservadores("POKEMON_CAPTURADO", pokemonSelvagem);
            verificarFimDeJogo();
        } else {
            notificarObservadores("CAPTURA_FALHOU", pokemonSelvagem);
            celulaOriginal.setPokemon(null);
            Celula novaCelula = tabuleiro.encontrarCelulaParaFuga(pokemonSelvagem, celulaOriginal.getLinha(), celulaOriginal.getColuna());
            if (novaCelula != null) {
                novaCelula.setPokemon(pokemonSelvagem);
            } else {
                pokemonsSelvagensRestantes--;
                verificarFimDeJogo();
            }
        }
    }

    private void verificarFimDeJogo() {
        if (pokemonsSelvagensRestantes <= 0) {
            this.jogoTerminou = true;
            notificarObservadores("JOGADA_CONCLUIDA", null); // Notifica uma última vez para a JanelaJogo detectar o fim.
        }
    }
    
    public boolean usarDica(int linha, int coluna) {
        if (dicasRestantes > 0) {
            dicasRestantes--;
            return tabuleiro.verificarDica(linha, coluna);
        }
        return false;
    }

    public void ativarModoDebug() {
        this.modoDebugAtivo = !this.modoDebugAtivo;
        notificarObservadores("JOGADA_CONCLUIDA", null);
    }
    
    public void adicionarObservador(ObservadorJogo observador) {
        if (observadores == null) observadores = new ArrayList<>();
        this.observadores.add(observador);
    }
    
    public void removerObservador(ObservadorJogo observador) {
        if (observadores != null) {
            this.observadores.remove(observador);
        }
    }

    public void notificarObservadores(String evento, Object dados) {
        jogador.atualizarPontuacao();
        computador.atualizarPontuacao();
        // Evita ConcurrentModificationException
        List<ObservadorJogo> observadoresCopia = new ArrayList<>(this.observadores);
        for (ObservadorJogo obs : observadoresCopia) {
            obs.atualizar(evento, dados);
        }
    }
    
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.observadores = new ArrayList<>();
        this.computador.setJogo(this);
    }

    // --- Getters ---
    public Tabuleiro getTabuleiro() { return tabuleiro; }
    public Treinador getJogador() { return jogador; }
    public Computador getComputador() { return computador; }
    public boolean isTurnoDoJogador() { return turnoDoJogador; }
    public boolean isJogoTerminou() { return jogoTerminou; }
    public int getDicasRestantes() { return dicasRestantes; }
    public boolean isModoDebugAtivo() { return modoDebugAtivo; }
    public Pokemon getPokemonJogadorBatalha() { return pokemonJogadorBatalha; }
    public Pokemon getPokemonOponenteBatalha() { return pokemonOponenteBatalha; }
}