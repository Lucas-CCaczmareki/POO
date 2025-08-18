package projetofinal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
 *  Serializable permite que os objetos dessa classe podem ser convertidos
 *  em uma sequência de bytes. É isso que permite salvar o estado dos pokémons em um arquivo
 */

/*
 * Essa classe é o coração do jogo, responsável por controlar o tabuleiro, os jogadores
 * as capturas, dicas, turnos e eventos do jogo.
 */
public class Jogo implements Serializable {

    //Atributos
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

    //Construtor da distribuição aleatória
    public Jogo() {
        inicializarComponentesBasicos();
        configuracaoInicialAleatoria();
    }

    //Construtor do setup manual
    public Jogo(boolean setupManual) {
        inicializarComponentesBasicos();
        
    }

    //Método auxiliar do construtor
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

    /*
     * Esse método é responsável pela distribuição inicial aleatória do tabuleiro
     */
    private void configuracaoInicialAleatoria() {
        //Faz uma lista com o nome de todos pokémons disponíveis e embaralha p/ garantir aleatoriedade
        List<String> nomesDisponiveis = new ArrayList<>(Arrays.asList(PokemonFactory.getPokemonsDisponiveis()));
        Collections.shuffle(nomesDisponiveis);

        //Remove o primeiro pokémon da lista, salva e torna esse o pokémon do jogador
        String nomePkmJogador = nomesDisponiveis.remove(0);
        Pokemon pJogador = PokemonFactory.criarPokemon(nomePkmJogador); //cria o pkm
        jogador.adicionarPokemonInicial(pJogador);                      //torna ele o inicial
        posicionarAleatoriamente(pJogador);                             //posiciona o Pokémon do jogador

        //Remove o primeiro da lista, salva e torna esse o pokémon do computador
        String nomePkmComputador = nomesDisponiveis.remove(0);          
        Pokemon pComputador = PokemonFactory.criarPokemon(nomePkmComputador); //cria opkm
        computador.adicionarPokemonInicial(pComputador);                      //Torna ele o inicial da CPU
        posicionarAleatoriamente(pComputador);                                //Posiciona aleatoriamente no tabuleiro
        
        //Pra cada pokémon que sobrou na lista, cria um pokémon selvagem e posiciona
        for (String nomeSelvagem : nomesDisponiveis) {
            Pokemon pSelvagem = PokemonFactory.criarPokemon(nomeSelvagem);
            posicionarAleatoriamente(pSelvagem);
            pokemonsSelvagensRestantes++;   //mantém um controle de quantos pokémons selvagens existem
        }
    }

    /*
     * Controla quantos pokémons selvagens existem
     */
    public void incrementarPokemonsSelvagens() {
        this.pokemonsSelvagensRestantes++;
    }

    /*
     * Esse método seleciona uma posição válida pra um pokémon aleatóriamente
     */
    private void posicionarAleatoriamente(Pokemon pokemon) {
        //Loopa até achar uma posição válida (com o número de pokémons atuais isso é ok)
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

    /*
     * Provavelmente tem o action listener da célula que ativa isso aqui
     * 
     */
    public void processarJogadaJogador(int linha, int coluna) {
        //As jogadas do jogador são ignoradas se não for o turno dele
        //(isso é importante perceber, o jogo continua responsivo enquanto a thread da CPU faz a sua jogada)
        if (!turnoDoJogador || jogoTerminou || emBatalha) return;
        processarLogicaDaCelula(jogador, linha, coluna);
        if (!emBatalha) {
            passarTurnoParaComputador();
        }
    }

    /*
     * Esse método é chamado pela thread do oomputador quando ele faz a jogada dele.
     * A thread do jogador só é ativada (e portanto processada) no método passarTurno
     */
    public void processarJogadaComputador(int linha, int coluna) {
        processarLogicaDaCelula(computador, linha, coluna);
        if (!emBatalha) {
            this.turnoDoJogador = true;
            //Notificamos os observadores (interface) para avisar que o turno voltou p/ jogador
            notificarObservadores("JOGADA_CONCLUIDA", null);
        }
    }
    
    /*
     * Esse método é responsável por...
     * 
     */
    private void processarLogicaDaCelula(Treinador treinadorAtivo, int linha, int coluna) {
        //Copia a instância da célula que foi criada e testa se ela já foi revelada
        Celula celula = tabuleiro.getGrade()[linha][coluna];
        if (celula.isRevelada()) return;
        
        //Se a célula não estava revelada antes, revela ela agora
        celula.revelar(treinadorAtivo);

        Pokemon pEncontrado = celula.getPokemon();

        //Se encontrou um pokémon na célula
        if (pEncontrado != null) {
            //Testa se é selvagem e inicia o método de captura
            if (pEncontrado.isSelvagem()) {
                iniciarCaptura(treinadorAtivo, pEncontrado, celula);
            //Se não for selvagem, testa se o pokemon principal do jogador do turno é diferente do encontrado
            } else if (treinadorAtivo.getPokemonPrincipal() != pEncontrado) {

                //Se for diferente, vê qual dos jogadores tá jogando
                Treinador oponente = (treinadorAtivo == jogador) ? computador : jogador;
                
                //Se o ativo for o computador, notifica a interface que o computador iniciou uma batalha
                if (treinadorAtivo == computador) {
                    notificarObservadores("COMPUTADOR_DESAFIOU", pEncontrado);
                }

                //Atualiza a célula batalha com a célula onde está ocorrendo uma batalha
                //e chama o método para iniciar a batalha
                this.celulaBatalha = celula;
                prepararParaBatalha(treinadorAtivo, oponente);
            }
        }
    }
    
    /*
     * Esse é o método responsável por iniciar a Thread do computador
     */
    private void passarTurnoParaComputador() {
        if (emBatalha) return;
        this.turnoDoJogador = false;
        notificarObservadores("JOGADA_CONCLUIDA", null);
        new Thread(computador).start();
    }
    
    /*
     * Seta as variáveis de controle da batalha com os dados necessários
     * e notifica o observador que iniciou uma batalha.
     */
    public void prepararParaBatalha(Treinador atacante, Treinador defensor) {
        this.emBatalha = true;
        this.atacanteOriginal = atacante;
        this.defensorOriginal = defensor;
        this.pokemonJogadorBatalha = atacante.getPokemonPrincipal();
        this.pokemonOponenteBatalha = defensor.getPokemonPrincipal();
        notificarObservadores("BATALHA_INICIADA", null);
    }
    
    /*
     * Método responsável por controlar um turno completo de batalha entre 2 Pokémons
     * Aplica as habilidades especiais, calcula os danos, verifica paralisia, cura e resistência
     * Notifica a interface sobre o que aconteceu e foi calculado.
     * 
     * Ele faz isso pros 2 treinadores, primeiro para o jogador e depois para o computador
     */
    public void executarTurnoBatalha() {
        if (!emBatalha) return;
        
        /* Sobre o StringBuilder
         * Um string builder é uma classe java para manipular textos de forma mais eficiente
         * Quando várias partes de texto precisam ser juntadas o string builder pe melhor
         * do que ficar usando o + no System.out
         * 
         * O string builder também modifica o texto internamente, o que evita que uma string
         * seja criada a cada modificação nela.
         */

        //Cria um log para registrar tudo que acontece no turno
        StringBuilder logDoTurno = new StringBuilder();

        //Pega no número do turno e o tipo da célula atual
        //(é importante para calcular as habilidades especiais)
        int turnoBatalha = this.numeroDoTurno;
        String tipoRegiao = tabuleiro.getTipoRegiao(celulaBatalha.getLinha(), celulaBatalha.getColuna());
        
        // --- Turno do Atacante (Jogador) ---
        logDoTurno.append("--- Turno de ").append(atacanteOriginal.getNome()).append(" ---\n");
        
        //Executa isso se o pokémon NÃO está paralisado
        if (!pokemonJogadorBatalha.isParalisado()) {

            /* Sobre instanceof
             * O instanceof verifica se um objeto é de um determinado tipo (classe ou interface)
             * E então ela retorna true ou false.
             * 
             * Então quando fazemos pokemon instance of PokemonFloresta ?
             * estamos perguntando se esse pokémon é do tipo floresta, e o sistema retora sim ou não
             */

            // Habilidade de Floresta
            if (pokemonJogadorBatalha instanceof PokemonFloresta) {
                //Aplica a cura com base na força total do Pokémon
                int cura = (int) (pokemonJogadorBatalha.getForca() * 0.35);
                logDoTurno.append(pokemonJogadorBatalha.getNome()).append(" se regenerou em ").append(cura).append(" de vida!\n");
            }
            
            //Calcula o dano causado pelo jogador
            int danoCausado = pokemonJogadorBatalha.calcularDano(turnoBatalha);

            // Habilidade de Terra
            if (pokemonJogadorBatalha instanceof PokemonTerra && turnoBatalha % 2 != 0) {
                //Só adiciona a mensagem. A lógica de dobrar dano acontece dentro da classe PokemonTerra
                logDoTurno.append(pokemonJogadorBatalha.getNome()).append(" usou sua fúria! O dano foi dobrado para ").append(danoCausado).append("!\n");
            }
            
            // Habilidade de Água do Oponente (Defensiva)
            if (pokemonOponenteBatalha instanceof PokemonAgua && !tipoRegiao.equals("Água")) {
                //Testa se o OPONENTE é de ÁGUA e está em terreno ADVERSO
                //SE ele estiver, reduz o dano causado pelo Pokémon
                int danoOriginal = danoCausado;
                danoCausado = (int) (danoCausado * 0.8);
                logDoTurno.append(pokemonOponenteBatalha.getNome()).append(" resistiu em ambiente adverso! Dano reduzido de ").append(danoOriginal).append(" para ").append(danoCausado).append(".\n");
            }
            
            //Faz o pokémon do oponente receber o dano causado e adiciona a mensagem
            pokemonOponenteBatalha.receberDano(danoCausado);
            logDoTurno.append(pokemonJogadorBatalha.getNome()).append(" causou ").append(danoCausado).append(" de dano!\n");
            
            // Habilidade de Elétrico
            if (pokemonJogadorBatalha instanceof PokemonEletrico && ((PokemonEletrico) pokemonJogadorBatalha).tentarParalisar()) {
                //O casting é necessário na condição porque pokemonJogadorBatalha é do tipo Pokemon
                //e o método da paralisia só existe na subclasse
                pokemonOponenteBatalha.setParalisado(true);
                logDoTurno.append(pokemonOponenteBatalha.getNome()).append(" está paralisado!\n");
            }

        //se o pokémon está paralisado, executa isso
        } else { 
             logDoTurno.append(pokemonJogadorBatalha.getNome()).append(" está paralisado e não pode atacar!\n");
             pokemonJogadorBatalha.setParalisado(false);
        }


        //Se a vida do oponente foi reduzida à zero ou abaixo disso a batalha acabou
        if (pokemonOponenteBatalha.getEnergia() <= 0) {
            notificarObservadores("BATALHA_ATUALIZADA", logDoTurno.toString());
            encerrarBatalha(pokemonJogadorBatalha, atacanteOriginal);
            return;
        }

        //Se o Oponente não foi derrotado, executa o turno dele. 
        //A lógica é espelhada para todos efeitos.

        logDoTurno.append("\n");

        // --- Turno do Oponente ---
        logDoTurno.append("--- Turno de ").append(defensorOriginal.getNome()).append(" ---\n");
        if (!pokemonOponenteBatalha.isParalisado()) {
            // Habilidade de Floresta do Oponente
            if (pokemonOponenteBatalha instanceof PokemonFloresta) {
                int cura = (int) (pokemonOponenteBatalha.getForca() * 0.35);
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
            notificarObservadores("BATALHA_ATUALIZADA", logDoTurno.toString());
            encerrarBatalha(pokemonOponenteBatalha, defensorOriginal);
            return;
        }
        
        logDoTurno.append("\n");
        this.numeroDoTurno++;
        notificarObservadores("BATALHA_ATUALIZADA", logDoTurno.toString());
    }

    /*
     * Método responsável por trocar o Pokémon do Jogador no meio de uma batalha
     */
    public void executarTrocaPokemonBatalha(int indiceNaMochila) {
        if (!emBatalha) return;
        
        //Instancia o log pra salvar a mensagem
        StringBuilder logDoTurno = new StringBuilder();

        //Troca o pokémon ativo do jogador e imprime uma mensagem
        atacanteOriginal.trocarPokemonPrincipal(indiceNaMochila);
        this.pokemonJogadorBatalha = atacanteOriginal.getPokemonPrincipal();
        logDoTurno.append(atacanteOriginal.getNome()).append(" trocou para ").append(pokemonJogadorBatalha.getNome()).append("!\n");

        //O pokémon que é trocado durante a batalha recebe um ataque de oportunidade
        int danoRecebido = pokemonOponenteBatalha.calcularDano(numeroDoTurno);
        pokemonJogadorBatalha.receberDano(danoRecebido);
        logDoTurno.append(pokemonOponenteBatalha.getNome()).append(" aproveitou a troca e causou ").append(danoRecebido).append(" de dano!\n");

        //Se o pokémon foi derrotado, encerra a batalha
        if (pokemonJogadorBatalha.getEnergia() <= 0) {
            encerrarBatalha(pokemonOponenteBatalha, defensorOriginal);
            return;
        }

        //Incrementa o turno e atualiza a interface, notificando os observers
        this.numeroDoTurno++;
        notificarObservadores("BATALHA_ATUALIZADA", logDoTurno.toString());
    }

    /*
     * Esse método é lançado pelo botão de fuga na tela de batalha
     * Ele restaura a energia dos lutadores, finaliza a batalha e passa o turno para o próximo
     */
    public void executarFugaBatalha() {
        //finaliza a batalha
        this.emBatalha = false;
        
        //restaura energias
        pokemonJogadorBatalha.restaurarEnergia();
        pokemonOponenteBatalha.restaurarEnergia();

        //Atualiza a tela
        notificarObservadores("BATALHA_TERMINADA", atacanteOriginal.getNome() + " fugiu da batalha!");

        //Passa o turno para o próximo
        if (atacanteOriginal == jogador) {
             passarTurnoParaComputador();
        } else {
            this.turnoDoJogador = true;
            notificarObservadores("JOGADA_CONCLUIDA", null);
        }
    }

    /*
     * Método responsável por encerrar a batalha, restaurar energias e atualizar a pontuação
     */
    private void encerrarBatalha(Pokemon vencedor, Treinador treinadorVencedor) {
        //finaliza a batalha
        this.emBatalha = false;

        //restaura energia
        pokemonJogadorBatalha.restaurarEnergia();
        pokemonOponenteBatalha.restaurarEnergia();

        //atualiza a pontuação
        vencedor.aumentarPontosDeExperiencia(50);

        //atualiza interface e passa o turno
        notificarObservadores("BATALHA_TERMINADA", vencedor.getNome() + " venceu a batalha!");
        if (atacanteOriginal == jogador) {
             passarTurnoParaComputador();
        } else {
            this.turnoDoJogador = true;
            notificarObservadores("JOGADA_CONCLUIDA", null);
        }
    }

    /*
     * Método responsável pelo processo de captura
     */
    private void iniciarCaptura(Treinador treinador, Pokemon pokemonSelvagem, Celula celulaOriginal) {
        // Lançamento da moeda (50% de chance)
        if (new Random().nextBoolean()) {
            // --- CASO 1: Captura bem-sucedida ---
            treinador.capturarPokemon(pokemonSelvagem);
            celulaOriginal.setPokemon(null);
            pokemonsSelvagensRestantes--;
            
            if (treinador == computador) {
                notificarObservadores("COMPUTADOR_CAPTUROU", pokemonSelvagem);
            } else {
                notificarObservadores("POKEMON_CAPTURADO", pokemonSelvagem);
            }
            
            //Verifica se foi a ultima captura para finalizar o jogo
            verificarFimDeJogo();
        } else {
            // --- CASO 2: Captura falhou ---
            celulaOriginal.setPokemon(null); 
            
            // Tenta encontrar uma nova célula para a fuga
            Celula novaCelula = tabuleiro.encontrarCelulaParaFuga(pokemonSelvagem, celulaOriginal.getLinha(), celulaOriginal.getColuna());
            
            //Se encontrou uma célula válida para fuga
            if (novaCelula != null) {
                //Move o pokémon até a célula válida
                novaCelula.setPokemon(pokemonSelvagem);
                
                // Envia a notificação correta dependendo de quem tentou a captura
                if (treinador == computador) {
                    notificarObservadores("COMPUTADOR_FALHOU_CAPTURA", pokemonSelvagem);
                } else {
                    notificarObservadores("CAPTURA_FALHOU", pokemonSelvagem);
                }
            //Se não tem uma célula válida para fuga
            } else {
                //Mantém o pokémon na célula onde ele está e mantém o estado dela
                celulaOriginal.setPokemon(pokemonSelvagem);
                
                celulaOriginal.setRevelada(false);
                
                notificarObservadores("FUGA_SEM_SAIDA", pokemonSelvagem);
            }
        }
    }

    /*
     * Verifica se os pokémons selvagens já foram todos capturados
     * e finaliza o jogo se sim
     */
    private void verificarFimDeJogo() {
        if (pokemonsSelvagensRestantes <= 0) {
            this.jogoTerminou = true;
            notificarObservadores("JOGADA_CONCLUIDA", null);
        }
    }
    
    /*
     * Método responsável por usar uma dica, chamando um método do tabuleiro que verifica
     * o estado da linha e da coluna para determinar a dica.
     * 
     * Esse método é chamado pelo action listener do botão de dica
     */
    public boolean usarDica(int linha, int coluna) {
        if (dicasRestantes > 0) {
            dicasRestantes--;
            return tabuleiro.verificarDica(linha, coluna);
        }
        //Se as dicas já acabaram só não faz nada
        return false;
    }

    /*
     * Método que ativa e desativa o debug, atualizando a interface para mostrar os 
     * pokémons.
     * 
     * É chamado pelo action listener do botão debug
     */
    public void ativarModoDebug() {
        this.modoDebugAtivo = !this.modoDebugAtivo;
        notificarObservadores("JOGADA_CONCLUIDA", null);
    }
    
    /*
     * Esses dois métodos permitm que as partes do sistema, por exemplo as interfaces
     * se registrem para receber notificações do jogo.
     * É uma parte da implementação do observer
     */
    public void adicionarObservador(ObservadorJogo observador) {
        if (observadores == null) observadores = new ArrayList<>();
        this.observadores.add(observador);
    }
    public void removerObservador(ObservadorJogo observador) {
        if (observadores != null) {
            this.observadores.remove(observador);
        }
    }

    /*
     * Atualiza todas classes que forem notificadas por um evento 
     */
    public void notificarObservadores(String evento, Object dados) {
        jogador.atualizarPontuacao();
        computador.atualizarPontuacao();
        List<ObservadorJogo> observadoresCopia = new ArrayList<>(this.observadores);
        for (ObservadorJogo obs : observadoresCopia) {
            obs.atualizar(evento, dados);
        }
    }
    
    /*
     * Esse método garante que ao carregar um jogo salvo, os campos que não são
     * serializados (os transient) sejam reinicializados e que as referências internas
     * fiquem consistentes.
     */
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