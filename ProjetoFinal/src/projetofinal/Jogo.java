package projetofinal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe controladora principal ("motor" do jogo).
 * Orquestra o fluxo da partida, conectando o tabuleiro, os treinadores e
 * implementando todas as regras e lógicas de jogo descritas no documento.
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
    private boolean modoDebugAtivo;

    private transient List<ObservadorJogo> observadores;

    public Jogo() {
        this.observadores = new ArrayList<>();
        this.tabuleiro = new Tabuleiro(8);
        this.jogador = new Treinador("Jogador 1");
        this.computador = new Computador("Computador", this);
        this.turnoDoJogador = true;
        this.numeroDoTurno = 1;
        this.dicasRestantes = 3; // ATIVIDADE 2: Máximo de 3 dicas por jogo [cite: 115]
        this.jogoTerminou = false;
        this.modoDebugAtivo = false;

        configuracaoInicialAleatoria();
    }

    /**
     * [cite_start]ATIVIDADE 1: Posição Aleatória [cite: 104-106]
     * Prepara o jogo, criando os Pokémon e posicionando-os aleatoriamente no tabuleiro.
     */
    private void configuracaoInicialAleatoria() {
        pokemonsSelvagensRestantes = 0;
        Random random = new Random();

        Pokemon pJogador = new PokemonAgua("Squirtle", 100, 25, 5);
        Pokemon pComputador = new PokemonEletrico("Pikachu", 90, 28, 5);
        Pokemon[] selvagens = {
                new PokemonFloresta("Bulbasaur", 110, 22, 4),
                new PokemonTerra("Sandshrew", 95, 26, 4),
                new PokemonAgua("Poliwag", 80, 20, 3),
                new PokemonEletrico("Voltorb", 75, 24, 3)
        };

        jogador.adicionarPokemonInicial(pJogador); // Cada treinador possui inicialmente um pokémon [cite: 67]
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
                    tabuleiro.posicionarPokemon(pokemon, linha, coluna); // Respeitando sua região [cite: 58]
                    break;
                }
            } catch (RegiaoInvalidaException | IllegalStateException e) {
                // Tenta novamente
            }
        }
    }

    public void processarJogadaJogador(int linha, int coluna) {
        if (!turnoDoJogador || jogoTerminou) return;
        processarLogicaDaCelula(jogador, linha, coluna);
        passarTurno();
    }

    public void processarJogadaComputador(int linha, int coluna) {
        processarLogicaDaCelula(computador, linha, coluna);
        this.turnoDoJogador = true;
        notificarObservadores("JOGADA_CONCLUIDA", null);
    }

    private void processarLogicaDaCelula(Treinador treinador, int linha, int coluna) {
        Celula celula = tabuleiro.getGrade()[linha][coluna];
        if (celula.isRevelada()) return;
        celula.setRevelada(true);
        
        // Marcar como revelada pelo jogador se for o jogador humano
        if (treinador == jogador) {
            celula.setReveladaPeloJogador(true);
        }

        Pokemon pEncontrado = celula.getPokemon();

        if (pEncontrado == null) {
            // Um objeto do cenário... ou nada [cite: 122]
        } else if (pEncontrado.isSelvagem()) {
            iniciarCaptura(treinador, pEncontrado, celula); // Pokémon Selvagem [cite: 123]
        } else if (treinador.getPokemonPrincipal() != pEncontrado) {
            Treinador oponente = (treinador == jogador) ? computador : jogador;
            iniciarBatalha(treinador, oponente); // Pokémon de outro treinador [cite: 125]
        }
    }

    private void iniciarCaptura(Treinador treinador, Pokemon pokemonSelvagem, Celula celulaOriginal) {
        if (new Random().nextBoolean()) { // Lançamento de uma moeda 'cara'-não ou 'coroa'-sim [cite: 123]
            treinador.capturarPokemon(pokemonSelvagem);
            celulaOriginal.setPokemon(null);
            pokemonsSelvagensRestantes--;
            notificarObservadores("POKEMON_CAPTURADO", pokemonSelvagem);
            verificarFimDeJogo();
        } else {
            notificarObservadores("CAPTURA_FALHOU", pokemonSelvagem);
            celulaOriginal.setPokemon(null);
            // Pokémon foge para outra célula não ocupada ao redor [cite: 124]
            Celula novaCelula = tabuleiro.encontrarCelulaParaFuga(pokemonSelvagem, celulaOriginal.getLinha(), celulaOriginal.getColuna());
            if (novaCelula != null) {
                novaCelula.setPokemon(pokemonSelvagem);
            } else {
                pokemonsSelvagensRestantes--;
                verificarFimDeJogo();
            }
        }
    }

    private void iniciarBatalha(Treinador atacante, Treinador defensor) {
        Pokemon pAtacante = atacante.getPokemonPrincipal();
        Pokemon pDefensor = defensor.getPokemonPrincipal();
        boolean defensorParalisado = false;

        while (pAtacante.getEnergia() > 0 && pDefensor.getEnergia() > 0) {
            if (!pAtacante.isParalisado()) {
                pDefensor.receberDano(pAtacante.calcularDano(numeroDoTurno));
                if (pAtacante instanceof PokemonEletrico && ((PokemonEletrico) pAtacante).tentarParalisar()) {
                    defensorParalisado = true;
                }
            }
            if (pDefensor.getEnergia() <= 0) break;

            if (!defensorParalisado) {
                pAtacante.receberDano(pDefensor.calcularDano(numeroDoTurno));
            } else {
                defensorParalisado = false;
            }
            if (pAtacante.getEnergia() <= 0) break;

            numeroDoTurno++;
        }

        (pAtacante.getEnergia() > 0 ? pAtacante : pDefensor).aumentarPontosDeExperiencia(50); // Pokémon vencedor ganha pontos de experiência [cite: 127]
        pAtacante.restaurarEnergia(); // Energias restabelecidas ao término da batalha [cite: 128]
        pDefensor.restaurarEnergia();
    }

    private void passarTurno() {
        this.turnoDoJogador = false;
        notificarObservadores("JOGADA_CONCLUIDA", null);
        new Thread(computador).start(); // INFORMAÇÕES IMPORTANTES: Deve fazer uso de Threads para Jogada do Computador [cite: 137]
    }

    private void verificarFimDeJogo() {
        if (pokemonsSelvagensRestantes <= 0) { // ATIVIDADE 3: O jogo termina quando todos os Pokémon selvagens tiverem sido capturados. [cite: 133]
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

    public void toggleModoDebug() {
        modoDebugAtivo = !modoDebugAtivo;
        
        if (modoDebugAtivo) {
            // Ativar debug - revelar todas as células
            for (Celula[] linha : tabuleiro.getGrade()) {
                for (Celula celula : linha) {
                    celula.setRevelada(true);
                }
            }
        } else {
            // Desativar debug - esconder células que não foram reveladas pelo jogador
            for (Celula[] linha : tabuleiro.getGrade()) {
                for (Celula celula : linha) {
                    // Só mantém revelada se foi revelada pelo jogador
                    celula.setRevelada(celula.isReveladaPeloJogador());
                }
            }
        }
        
        notificarObservadores("DEBUG_TOGGLED", null);
    }

    public void adicionarObservador(ObservadorJogo observador) {
        if (observadores == null) {
            observadores = new ArrayList<>();
        }
        this.observadores.add(observador);
    }

    public void notificarObservadores(String evento, Object dados) {
        jogador.atualizarPontuacao();
        computador.atualizarPontuacao();
        for (ObservadorJogo obs : this.observadores) {
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
}