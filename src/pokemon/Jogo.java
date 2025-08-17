package pokemon;

// IMPORT ADICIONADO AQUI
import javax.swing.JOptionPane;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Versão final da classe Jogo com a lógica de batalha completa,
 * incluindo a opção de fugir, implementada de forma segura.
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

    private void configuracaoInicialAleatoria() {
        pokemonsSelvagensRestantes = 0;

        // AGORA USA A FACTORY PARA CRIAR OS POKÉMON
        Pokemon pJogador = PokemonFactory.criarPokemon("Squirtle");
        Pokemon pComputador = PokemonFactory.criarPokemon("Pikachu");
        Pokemon[] selvagens = {
            PokemonFactory.criarPokemon("Bulbasaur"),
            PokemonFactory.criarPokemon("Sandshrew")
        };

        jogador.adicionarPokemonInicial(pJogador);
        computador.adicionarPokemonInicial(pComputador);

        posicionarAleatoriamente(pJogador);
        posicionarAleatoriamente(pComputador);

        for (Pokemon p : selvagens) {
            posicionarAleatoriamente(p);
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
        if (!turnoDoJogador || jogoTerminou) return;
        processarLogicaDaCelula(jogador, linha, coluna);
        passarTurnoParaComputador();
    }

    public void processarJogadaComputador(int linha, int coluna) {
        processarLogicaDaCelula(computador, linha, coluna);
        this.turnoDoJogador = true;
        notificarObservadores("JOGADA_CONCLUIDA", null);
    }
    
    private void processarLogicaDaCelula(Treinador treinadorAtivo, int linha, int coluna) {
        if (emBatalha) return; // Não faz nada se já estiver em batalha
        
        Celula celula = tabuleiro.getGrade()[linha][coluna];
        if (celula.isRevelada()) return;
        celula.setRevelada(true);

        Pokemon pEncontrado = celula.getPokemon();

        if (pEncontrado != null) {
            if (pEncontrado.isSelvagem()) {
                iniciarCaptura(treinadorAtivo, pEncontrado, celula);
            } else if (treinadorAtivo.getPokemonPrincipal() != pEncontrado) {
                Treinador oponente = (treinadorAtivo == jogador) ? computador : jogador;
                prepararParaBatalha(treinadorAtivo, oponente); // << MUDANÇA AQUI
            }
        }
    }
    
    private void passarTurnoParaComputador() {
        if (emBatalha) return; // Não passa o turno se uma batalha começou
        this.turnoDoJogador = false;
        notificarObservadores("JOGADA_CONCLUIDA", null);
        new Thread(computador).start();
    }
    // --- LÓGICA DE BATALHA COMPLETAMENTE REFEITA ---
    public void prepararParaBatalha(Treinador atacante, Treinador defensor) {
        this.emBatalha = true;
        this.atacanteOriginal = atacante;
        this.defensorOriginal = defensor;
        this.pokemonJogadorBatalha = atacante.getPokemonPrincipal();
        this.pokemonOponenteBatalha = defensor.getPokemonPrincipal();
        
        // Notifica a JanelaJogo para abrir a JanelaBatalha
        notificarObservadores("BATALHA_INICIADA", null);
    }
    public void executarTurnoBatalha() {
        if (!emBatalha) return;
        
        StringBuilder logDoTurno = new StringBuilder();
        
        // Jogador (atacante original) ataca
        int danoCausado = pokemonJogadorBatalha.calcularDano(numeroDoTurno);
        pokemonOponenteBatalha.receberDano(danoCausado);
        logDoTurno.append(pokemonJogadorBatalha.getNome()).append(" causou ").append(danoCausado).append(" de dano!\n");

        if (pokemonOponenteBatalha.getEnergia() <= 0) {
            encerrarBatalha(pokemonJogadorBatalha, atacanteOriginal);
            return;
        }

        // Oponente (defensor original) ataca
        int danoRecebido = pokemonOponenteBatalha.calcularDano(numeroDoTurno);
        pokemonJogadorBatalha.receberDano(danoRecebido);
        logDoTurno.append(pokemonOponenteBatalha.getNome()).append(" causou ").append(danoRecebido).append(" de dano!\n");

        if (pokemonJogadorBatalha.getEnergia() <= 0) {
            encerrarBatalha(pokemonOponenteBatalha, defensorOriginal);
            return;
        }

        numeroDoTurno++;
        notificarObservadores("BATALHA_ATUALIZADA", logDoTurno.toString());
    }
    public void executarFugaBatalha() {
        this.emBatalha = false;
        pokemonJogadorBatalha.restaurarEnergia();
        pokemonOponenteBatalha.restaurarEnergia();
        notificarObservadores("BATALHA_TERMINADA", "Você fugiu da batalha!");
        passarTurnoParaComputador();
    }
    private void encerrarBatalha(Pokemon vencedor, Treinador treinadorVencedor) {
        this.emBatalha = false;
        pokemonJogadorBatalha.restaurarEnergia();
        pokemonOponenteBatalha.restaurarEnergia();
        vencedor.aumentarPontosDeExperiencia(50);
        notificarObservadores("BATALHA_TERMINADA", vencedor.getNome() + " venceu a batalha!");
        passarTurnoParaComputador();
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
        }
    }
    
    public boolean usarDica(int linha, int coluna) {
        if (dicasRestantes > 0) {
            dicasRestantes--;
            return tabuleiro.verificarDica(linha, coluna);
        }
        return false;
    }

    /**
     * CORRIGIDO: Agora alterna o estado do modo Debug.
     */
    public void ativarModoDebug() {
        this.modoDebugAtivo = !this.modoDebugAtivo; // Alterna entre true e false
        // Apenas notifica a janela, que será responsável pela lógica visual
        notificarObservadores("JOGADA_CONCLUIDA", null);
    }
    
    public void adicionarObservador(ObservadorJogo observador) {
        if (observadores == null) observadores = new ArrayList<>();
        this.observadores.add(observador);
    }

    public void notificarObservadores(String evento, Object dados) {
        jogador.atualizarPontuacao();
        computador.atualizarPontuacao();
        for (ObservadorJogo obs : this.observadores) {
            obs.atualizar(evento, dados);
        }
    }
     /**
     * ADICIONADO: Método para remover um observador da lista.
     * @param observador O observador a ser removido.
     */
    public void removerObservador(ObservadorJogo observador) {
        if (observadores != null) {
            this.observadores.remove(observador);
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
    // ADICIONE ESTE GETTER NO FINAL DA CLASSE
    public boolean isModoDebugAtivo() { return modoDebugAtivo; }
    public Pokemon getPokemonJogadorBatalha() { return pokemonJogadorBatalha; }
    public Pokemon getPokemonOponenteBatalha() { return pokemonOponenteBatalha; }
}