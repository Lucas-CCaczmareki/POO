package projetofinal;

import java.util.Random;

public class Jogo {
    private JanelaPrincipal view;

    private Tabuleiro tabuleiro;
    private Treinador jogador;
    private Computador computador;
    private boolean turnoDoJogador;
    private int contadorTurnos;
    
    private boolean emBatalha = false;
    private Pokemon pokemonJogadorBatalha;
    private Pokemon pokemonOponenteBatalha;

    private int linhaBatalha;
    private int colunaBatalha


    public Jogo(JanelaPrincipal view) {
        this.view = view;
        //Ainda não cria o tabuleiro, vai ter que iniciar o jogo
    }

    public void iniciarNovoJogo() {
        //É aqui que no futuro vai ter a lógica se o cara quer o setup aleatório ou não

        System.out.println("Iniciando novo jogo...");
        
        //Setup inicial
        this.tabuleiro = new Tabuleiro();
        this.turnoDoJogador = true;

        //Setup jogadores
        this.jogador = new Treinador("Ash");
        this.computador = new Computador("Gary", this); //passa a referência desse jogo

        //Setup inicial do tabuleiro pra testes
        setupInicialDoTabuleiro();

        //Threads
        Thread threadDoComputador = new Thread(this.computador);
        threadDoComputador.start(); //chama o run dentro da thread do computador em paralelo
    }

    /*
     * Fica isso aqui setupado pra testes, e depois de implementar a lógica de captura e batalha
     * eu penso nas lógicas de criar o tabuleiro
     */
    private void setupInicialDoTabuleiro() {
        System.out.println("Configurando o tabuleiro inicial...");

        // Lembre-se que o PDF descreve diferentes modos de início 
        // (aleatório, posicionado, etc.).
        // Por enquanto, vamos fazer um posicionamento fixo para teste.

        // Cria instâncias de Pokémon
        Pokemon p1 = new PokemonAgua("Squirtle");
        Pokemon p2 = new PokemonFloresta("Bulbasaur");
        Pokemon p3 = new PokemonTerra("Sandshrew");
        Pokemon p4 = new PokemonEletrico("Pikachu");

        // Associa alguns Pokémon ao jogador e ao computador
        jogador.addPkm(p1);
        computador.addPkm(p4);

        // Posiciona os Pokémon no tabuleiro, respeitando as regiões
        // Região Água: linhas 0 a 3, colunas 0 a 3 [cite: 61]
        tabuleiro.posicionarPokemon(p1, 1, 2);

        // Região Floresta: linhas 0 a 3, colunas 4 a 7 [cite: 62]
        tabuleiro.posicionarPokemon(p2, 0, 5);
        p2.setSelvagem(true); // Exemplo de Pokémon selvagem

        // Região Terra: linhas 4 a 7, colunas 0 a 3 [cite: 63]
        tabuleiro.posicionarPokemon(p3, 6, 1);
        p3.setSelvagem(true); // Exemplo de Pokémon selvagem
        
        // Região Eletricidade: linhas 4 a 7, colunas 4 a 7 [cite: 64]
        tabuleiro.posicionarPokemon(p4, 5, 5);
    }

    
    //Esse método é chamado pela classe que gerencia a interface gráfica, quando um jogador clica num botão
    public synchronized void jogadaDoJogador(int linha, int coluna) {
        //Aqui a linha e a coluna devem vir do clique do botão
        
        if(!turnoDoJogador) return; //Não faz nada se tiver na vez da CPU

        //System.out.println("Jogador clicou em [" + linha + "][" + coluna + "]");

        //Eu tenho que acessar a matriz do tabuleiro pra pegar a celula que tem a info do pokemon
        Celula celulaClica = tabuleiro.getCelula(linha, coluna);
        Pokemon pkmNaCelula = celulaClica.getPkm();

        //Antes disso tem que ver se tem um pokemon né
        if(pkmNaCelula != null) {
            //Se tem um pkm, confere se é selvagem
            if(pkmNaCelula.isSelvagem()){
                // CASO 1: O POKEMON É SELVAGEM
                System.out.println("Um " + pkmNaCelula.getNome() + " selvagem apareceu!");
                Random random = new Random();

                //Equivalente à jogar uma moeda (50% de chance)
                if(random.nextBoolean()) {
                    System.out.println("Você capturou um " + pkmNaCelula.getNome());
                    jogador.addPkm(pkmNaCelula); //Adiciona o pokemon na mochila do treinador

                    pkmNaCelula.setSelvagem(false); // o pkm deixa de ser selvagem
                    
                    //Isso é interessante pra quando for colocar a ação de capturar pro computador
                    pkmNaCelula.setDono(jogador);

                } else {
                    System.out.println("Oh não! O pokemon escapou!");
                    //Agora ele reposiciona em alguma célula vizinha
                    executarFuga(pkmNaCelula, linha, coluna);
                }
            } else {
                // CASO 2: O POKÉMON NÃO É SELVAGEM (pertence à um treinador)
                
                //Verifica se o pokemon pertence ao computador
                if (pkmNaCelula.getDono() == computador) {
                    //Guarda as coordenadas de onde a batalha ta acontecendo
                    this.linhaBatalha = linha;
                    this.colunaBatalha = coluna;

                    //Seleciona o pokemon ativo do jogador (1o na mochila)
                    Pokemon meuPokemon = jogador.getMochila().get(0);
                    iniciarBatalha(meuPokemon, pkmNaCelula);
                
                } else {
                    //Se não for do computar (também não é selvagem) é o pokemon que você posicionou
                    System.out.println("Este Pokémon já é seu!");
                }
            }
        } else {
            System.out.println("Posição vazia!");
        }

        //Se nenhuma batalha foi iniciada, passa vez
        if (!emBatalha) {
            this.turnoDoJogador = false;
            notifyAll(); //Fala pra thread do computador trabaia;
        }
    }

    public void iniciarBatalha(Pokemon jogadorPkm, Pokemon oponentePkm) {
        this.emBatalha = true;
        this.pokemonJogadorBatalha = jogadorPkm;
        this.pokemonOponenteBatalha = oponentePkm;

        view.getPainelBatalha().setLogInicial(jogadorPkm.getNome() + " vs " + oponentePkm.getNome());
        view.mostrarPainel("BATALHA");
    }

    public void executarRoundDeBatalha() {
        
        if(!emBatalha) return;
        contadorTurnos++; //Incrementa o contador de turnos

        //Jogador ataca
        int danoCausado = pokemonJogadorBatalha.calcularDano(pokemonOponenteBatalha, contadorTurnos);
        pokemonOponenteBatalha.receberDano(danoCausado, tabuleiro.getRegiaoDaCelula(linhaBatalha, colunaBatalha));

        view.getPainelBatalha().atualizarLog(pokemonJogadorBatalha.getNome() + " causou " + danoCausado + " de dano.");

        //Se você derrotou o pokemon do oponente
        if (pokemonOponenteBatalha.getEnergia() <= 0) {
            view.getPainelBatalha().atualizarLog(pokemonOponenteBatalha.getNome() + " desmaiou. Você venceu a batalha!");
            pokemonJogadorBatalha.setExperience(50);
            encerrarBatalha();
            return;
        }

        // Oponente Ataca
        int danoRecebido = pokemonOponenteBatalha.calcularDano(pokemonOponenteBatalha, contadorTurnos);
        pokemonJogadorBatalha.receberDano(danoRecebido, tabuleiro.getRegiaoDaCelula(linhaBatalha, colunaBatalha));

        //Verifica se o meu pokemon foi derrotado pelo oponenete
        if(pokemonJogadorBatalha.getEnergia() <= 0) {
            view.getPainelBatalha().atualizarLog(pokemonJogadorBatalha.getNome() + " desmaiou. Você perdeu a batalha!");
            pokemonOponenteBatalha.setExperience(50);
            encerrarBatalha();;
            return;
        }

    }

    public void encerrarBatalha() {
        this.emBatalha = false;
        this.contadorTurnos = 0;

        //Restaura a energia dos pokemons
        pokemonJogadorBatalha.restaurarEnergia();
        pokemonOponenteBatalha.restaurarEnergia();

        view.mostrarPainel("TABULEIRO");

        //Agora que a batalha acabou passa a vez pro computador
        this.turnoDoJogador = false;
        notifyAll();
    }

    public void tentarFugir() {
        view.getPainelBatalha().atualizarLog("Você fugiu da batalha!");
        encerrarBatalha();
    }

    private void executarFuga(Pokemon pkm, int linha, int coluna) {
        //Testa até achar uma posição livre

        //Cima-Esquerda
        if(isPosicaoValidaParaFuga(linha - 1, coluna -1, pkm)) {    //Se ta livre e é do mesmo tipo
            tabuleiro.getCelula(linha, coluna).setPkm(null);    //limpa a célula antiga
            tabuleiro.posicionarPokemon(pkm, linha-1, coluna-1);    //move pra célula livre
            return;
        }

        //Cima
        if(isPosicaoValidaParaFuga(linha - 1, coluna, pkm)) {       //Se ta livre e é do mesmo tipo
            tabuleiro.getCelula(linha, coluna).setPkm(null);    //limpa a célula antiga
            tabuleiro.posicionarPokemon(pkm, linha - 1, coluna);    //move pra célula livre
            return;
        }

        //Cima-Direita
        if(isPosicaoValidaParaFuga(linha - 1, coluna + 1, pkm)) {       //Se ta livre e é do mesmo tipo
            tabuleiro.getCelula(linha, coluna).setPkm(null);        //limpa a célula antiga
            tabuleiro.posicionarPokemon(pkm, linha - 1, coluna + 1);    //move pra célula livre
            return;
        }

        //Esquerda
        if(isPosicaoValidaParaFuga(linha, coluna - 1, pkm)) {       //Se ta livre e é do mesmo tipo
            tabuleiro.getCelula(linha, coluna).setPkm(null);    //limpa a célula antiga
            tabuleiro.posicionarPokemon(pkm, linha, coluna - 1);    //move pra célula livre
            return;
        }


        //Direita
        if(isPosicaoValidaParaFuga(linha, coluna + 1, pkm)) {       //Se ta livre e é do mesmo tipo
            tabuleiro.getCelula(linha, coluna).setPkm(null);    //limpa a célula antiga
            tabuleiro.posicionarPokemon(pkm, linha, coluna + 1);    //move pra célula livre
            return;
        }


        //Baixo-Esquerda
        if(isPosicaoValidaParaFuga(linha + 1, coluna - 1, pkm)) {       //Se ta livre e é do mesmo tipo
            tabuleiro.getCelula(linha, coluna).setPkm(null);        //limpa a célula antiga
            tabuleiro.posicionarPokemon(pkm, linha + 1, coluna - 1);    //move pra célula livre
            return;
        }


        //Baixo
        if(isPosicaoValidaParaFuga(linha + 1, coluna, pkm)) {       //Se ta livre e é do mesmo tipo
            tabuleiro.getCelula(linha, coluna).setPkm(null);    //limpa a célula antiga
            tabuleiro.posicionarPokemon(pkm, linha + 1, coluna);    //move pra célula livre
            return;
        }


        //Baixo-direita
        if(isPosicaoValidaParaFuga(linha + 1, coluna + 1, pkm)) {       //Se ta livre e é do mesmo tipo
            tabuleiro.getCelula(linha, coluna).setPkm(null);        //limpa a célula antiga
            tabuleiro.posicionarPokemon(pkm, linha + 1, coluna + 1);    //move pra célula livre
            return;
        }


        //Se chegou aqui ele não tem pra onde ir, fica onde está
        System.out.println("O pokémon não tem pra onde fugir!");
    }

    private boolean isPosicaoValidaParaFuga(int linha, int coluna, Pokemon pkm) {
        //Trata is index out of bounds que podem acontecer
        //Confere se é do mesmo tipo do pokemon e se ta livre

        if(linha < 0 || linha >= tabuleiro.getTamanhoN() || coluna < 0 || coluna >= tabuleiro.getTamanhoN()) {
            return false;
        }

        //confere se ta vazia
        boolean estaVazia = tabuleiro.getCelula(linha, coluna).getPkm() == null; 

        //confere se a regiao é do mesmo tipo do pkm
        boolean regiaoCorreta = tabuleiro.getRegiaoDaCelula(linha, coluna).equals(pkm.getTipo()); 

        return estaVazia && regiaoCorreta; //se ta vazia E é uma regiao valida, retorna true
    }


    // Método que o computador vai chamar
    public void jogadaDoComputador() {

        // PRECISA IMPLEMENTAR AINDA

        System.out.println("Computador está escolhendo uma jogada...");
        Random random = new Random();

        int linha = random.nextInt(tabuleiro.getTamanhoN());
        int coluna = random.nextInt(tabuleiro.getTamanhoN());


        System.out.println("-> Computador clicou em [" + linha + "][" + coluna + "]");

        //*********************************** !!!!!!!! *************************************
        //Aqui entraria a lógica de batalha/captura do computador que eu vou ignorar por enquanto

        // Troca pra vez do jogador
        this.turnoDoJogador = true;
    }

    //Método de sincronização para a Thread do computador esperar (?)
    public synchronized void esperarVezDoComputador() {
        while(turnoDoJogador) {
            try {
                wait(); //a thread da cpu espera
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

    //Getter pra interface gráfica acessar o tabuleiro
    public Tabuleiro getTabuleiro() {
        return this.tabuleiro;
    }
    
}
