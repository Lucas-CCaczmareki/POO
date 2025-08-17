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
    private boolean emBatalha;
    private Pokemon pokemonJogadorBatalha;
    private Pokemon pokemonOponenteBatalha;
    private Treinador atacanteOriginal;
    private Treinador defensorOriginal;
    private Celula celulaBatalha;
    private transient List<ObservadorJogo> observadores;

    /**
     * Construtor padrão: cria um jogo com configuração aleatória.
     */
    public Jogo() {
        inicializarComponentesBasicos();
        configuracaoInicialAleatoria();
    }

    /**
     * Construtor para modo de setup manual: apenas prepara o jogo, mas não posiciona os Pokémon.
     */
    public Jogo(boolean setupManual) {
        inicializarComponentesBasicos();
        // Não chama a configuração aleatória, deixando o tabuleiro limpo.
    }

    private void inicializarComponentesBasicos() {
        this.observadores = new ArrayList<>();
        this.tabuleiro = new Tabuleiro(8);
        this.jogador = new Treinador("Jogador 1");
        this.computador = new Computador("Computador", this);
        this.turnoDoJogador = true;
        this.numeroDoTurno = 1;
        this.dicasRestantes = 3;
        this.jogoTerminou = false;
        this.modoDebugAtivo = false;
        this.emBatalha = false;
        this.pokemonsSelvagensRestantes = 0;
    }

    private void configuracaoInicialAleatoria() {
        List<String> nomesDisponiveis = new ArrayList<>(Arrays.asList(PokemonFactory.getPokemonsDisponiveis()));
        Collections.shuffle(nomesDisponiveis);

        String nomePkmJogador = nomesDisponiveis.remove(0);
        Pokemon pJogador = PokemonFactory.criarPokemon(nomePkmJogador);
        jogador.adicionarPokemonInicial(pJogador);
        posicionarAleatoriamente(pJogador);

        String nomePkmComputador = nomesDisponiveis.remove(0);
        Pokemon pComputador = PokemonFactory.criarPokemon(nomePkmComputador);
        computador.adicionarPokemonInicial(pComputador);
        posicionarAleatoriamente(pComputador);
        
        for (String nomeSelvagem : nomesDisponiveis) {
            Pokemon pSelvagem = PokemonFactory.criarPokemon(nomeSelvagem);
            posicionarAleatoriamente(pSelvagem);
            pokemonsSelvagensRestantes++;
        }
    }

    public void incrementarPokemonsSelvagens() {
        this.pokemonsSelvagensRestantes++;
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
        
        // CORREÇÃO: Usa o novo método para registrar quem revelou
        celula.revelar(treinadorAtivo);

        Pokemon pEncontrado = celula.getPokemon();

        if (pEncontrado != null) {
            if (pEncontrado.isSelvagem()) {
                iniciarCaptura(treinadorAtivo, pEncontrado, celula);
            } else if (treinadorAtivo.getPokemonPrincipal() != pEncontrado) {
                Treinador oponente = (treinadorAtivo == jogador) ? computador : jogador;
                // NOVO: Notificação específica se for o computador que encontrou seu Pokémon
                if (treinadorAtivo == computador) {
                    notificarObservadores("COMPUTADOR_DESAFIOU", pEncontrado);
                }
                this.celulaBatalha = celula;
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
        
        // --- Turno do Atacante (Jogador) ---
        logDoTurno.append("--- Turno de ").append(atacanteOriginal.getNome()).append(" ---\n");
        if (!pokemonJogadorBatalha.isParalisado()) {
            // Habilidade de Floresta
            if (pokemonJogadorBatalha instanceof PokemonFloresta) {
                int cura = (int) (pokemonJogadorBatalha.getForca() * 0.2);
                logDoTurno.append(pokemonJogadorBatalha.getNome()).append(" se regenerou em ").append(cura).append(" de vida!\n");
            }
            
            int danoCausado = pokemonJogadorBatalha.calcularDano(turnoBatalha);

            // Habilidade de Terra
            if (pokemonJogadorBatalha instanceof PokemonTerra && turnoBatalha % 2 != 0) {
                logDoTurno.append(pokemonJogadorBatalha.getNome()).append(" usou sua fúria! O dano foi dobrado para ").append(danoCausado).append("!\n");
            }
            
            // Habilidade de Água do Oponente (Defensiva)
            if (pokemonOponenteBatalha instanceof PokemonAgua && !tipoRegiao.equals("Água")) {
                int danoOriginal = danoCausado;
                danoCausado = (int) (danoCausado * 0.8);
                logDoTurno.append(pokemonOponenteBatalha.getNome()).append(" resistiu em ambiente adverso! Dano reduzido de ").append(danoOriginal).append(" para ").append(danoCausado).append(".\n");
            }
            
            pokemonOponenteBatalha.receberDano(danoCausado);
            logDoTurno.append(pokemonJogadorBatalha.getNome()).append(" causou ").append(danoCausado).append(" de dano!\n");
            
            // Habilidade de Elétrico
            if (pokemonJogadorBatalha instanceof PokemonEletrico && ((PokemonEletrico) pokemonJogadorBatalha).tentarParalisar()) {
                pokemonOponenteBatalha.setParalisado(true);
                logDoTurno.append(pokemonOponenteBatalha.getNome()).append(" está paralisado!\n");
            }
        } else {
             logDoTurno.append(pokemonJogadorBatalha.getNome()).append(" está paralisado e não pode atacar!\n");
             pokemonJogadorBatalha.setParalisado(false);
        }

        if (pokemonOponenteBatalha.getEnergia() <= 0) {
            encerrarBatalha(pokemonJogadorBatalha, atacanteOriginal);
            return;
        }

        logDoTurno.append("\n");

        // --- Turno do Oponente ---
        logDoTurno.append("--- Turno de ").append(defensorOriginal.getNome()).append(" ---\n");
        if (!pokemonOponenteBatalha.isParalisado()) {
            // Habilidade de Floresta do Oponente
            if (pokemonOponenteBatalha instanceof PokemonFloresta) {
                int cura = (int) (pokemonOponenteBatalha.getForca() * 0.2);
                logDoTurno.append(pokemonOponenteBatalha.getNome()).append(" se regenerou em ").append(cura).append(" de vida!\n");
            }

            int danoRecebido = pokemonOponenteBatalha.calcularDano(turnoBatalha);

            // Habilidade de Terra do Oponente
            if (pokemonOponenteBatalha instanceof PokemonTerra && turnoBatalha % 2 != 0) {
                logDoTurno.append(pokemonOponenteBatalha.getNome()).append(" usou sua fúria! O dano foi dobrado para ").append(danoRecebido).append("!\n");
            }

            // Habilidade de Água do Jogador (Defensiva)
            if (pokemonJogadorBatalha instanceof PokemonAgua && !tipoRegiao.equals("Água")) {
                int danoOriginal = danoRecebido;
                danoRecebido = (int) (danoRecebido * 0.8);
                logDoTurno.append(pokemonJogadorBatalha.getNome()).append(" resistiu em ambiente adverso! Dano reduzido de ").append(danoOriginal).append(" para ").append(danoRecebido).append(".\n");
            }
            
            pokemonJogadorBatalha.receberDano(danoRecebido);
            logDoTurno.append(pokemonOponenteBatalha.getNome()).append(" causou ").append(danoRecebido).append(" de dano!\n");
            
            // Habilidade de Elétrico do Oponente
            if (pokemonOponenteBatalha instanceof PokemonEletrico && ((PokemonEletrico) pokemonOponenteBatalha).tentarParalisar()) {
                pokemonJogadorBatalha.setParalisado(true);
                logDoTurno.append(pokemonJogadorBatalha.getNome()).append(" está paralisado!\n");
            }
        } else {
             logDoTurno.append(pokemonOponenteBatalha.getNome()).append(" está paralisado e não pode atacar!\n");
             pokemonOponenteBatalha.setParalisado(false);
        }

        if (pokemonJogadorBatalha.getEnergia() <= 0) {
            encerrarBatalha(pokemonOponenteBatalha, defensorOriginal);
            return;
        }
        
        logDoTurno.append("\n");
        this.numeroDoTurno++;
        notificarObservadores("BATALHA_ATUALIZADA", logDoTurno.toString());
    }

    public void executarTrocaPokemonBatalha(int indiceNaMochila) {
        if (!emBatalha) return;
        StringBuilder logDoTurno = new StringBuilder();
        atacanteOriginal.trocarPokemonPrincipal(indiceNaMochila);
        this.pokemonJogadorBatalha = atacanteOriginal.getPokemonPrincipal();
        logDoTurno.append(atacanteOriginal.getNome()).append(" trocou para ").append(pokemonJogadorBatalha.getNome()).append("!\n");

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

    /**
     * MÉTODO COMPLETAMENTE REESCRITO PARA CORRIGIR O BUG DE NOTIFICAÇÃO
     */
    private void iniciarCaptura(Treinador treinador, Pokemon pokemonSelvagem, Celula celulaOriginal) {
        // Lançamento da moeda (50% de chance)
        if (new Random().nextBoolean()) {
            // --- CASO 1: Captura bem-sucedida ---
            treinador.capturarPokemon(pokemonSelvagem);
            celulaOriginal.setPokemon(null);
            pokemonsSelvagensRestantes--;
            
            // Envia a notificação correta dependendo de quem capturou
            if (treinador == computador) {
                notificarObservadores("COMPUTADOR_CAPTUROU", pokemonSelvagem);
            } else {
                notificarObservadores("POKEMON_CAPTURADO", pokemonSelvagem);
            }
            
            verificarFimDeJogo();
        } else {
            // --- CASO 2: Captura falhou ---
            celulaOriginal.setPokemon(null); // Tira o Pokémon da célula para tentar movê-lo
            
            // Tenta encontrar uma nova célula para a fuga
            Celula novaCelula = tabuleiro.encontrarCelulaParaFuga(pokemonSelvagem, celulaOriginal.getLinha(), celulaOriginal.getColuna());
            
            if (novaCelula != null) {
                // --- SUB-CASO 2.1: Fuga bem-sucedida ---
                novaCelula.setPokemon(pokemonSelvagem);
                
                // Envia a notificação correta dependendo de quem tentou a captura
                if (treinador == computador) {
                    notificarObservadores("COMPUTADOR_FALHOU_CAPTURA", pokemonSelvagem);
                } else {
                    notificarObservadores("CAPTURA_FALHOU", pokemonSelvagem);
                }
            } else {
                // --- SUB-CASO 2.2: Pokémon encurralado ---
                // Se não há para onde fugir, o Pokémon volta para a célula original...
                celulaOriginal.setPokemon(pokemonSelvagem);
                // ...e a célula é marcada como NÃO REVELADA para que possa ser clicada de novo.
                celulaOriginal.setRevelada(false);
                
                // Envia a notificação de que o Pokémon está encurralado
                notificarObservadores("FUGA_SEM_SAIDA", pokemonSelvagem);
            }
        }
    }

    private void verificarFimDeJogo() {
        if (pokemonsSelvagensRestantes <= 0) {
            this.jogoTerminou = true;
            notificarObservadores("JOGADA_CONCLUIDA", null);
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
    public Treinador getAtacanteOriginal() { return atacanteOriginal; }
}