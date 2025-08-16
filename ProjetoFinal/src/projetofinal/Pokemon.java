package projetofinal;

import java.io.Serializable;

/**
 * Representa a entidade Pokémon de forma abstrata.
 * Contém todas as características e ações base descritas no documento.
 * Implementa IAtaque para polimorfismo de ataque e Serializable para
 * [cite_start]permitir que o estado do jogo seja salvo em arquivo. [cite: 148]
 */
public abstract class Pokemon implements IAtaque, Serializable {


    private String nome;
    private String tipo;
    private int energia;
    private int energiaMaxima;
    private int forca;
    private int pontosDeExperiencia;
    private int nivel;
    private boolean selvagem;
    private boolean paralisado;

    public Pokemon(String nome, String tipo, int energia, int forca, int nivel) {
        this.nome = nome;
        this.tipo = tipo;
        this.energia = energia;
        this.energiaMaxima = energia;
        this.forca = forca;
        this.nivel = nivel;
        this.pontosDeExperiencia = 0;
        this.selvagem = true;
        this.paralisado = false;
    }

    /**
     * Método abstrato da interface IAtaque. Cada subclasse de Pokémon DEVE
     * [cite_start]implementar sua própria lógica de cálculo de dano. [cite: 82]
     */
    @Override
    public abstract int calcularDano(int numeroDoTurno);

    /**
     * [cite_start]Adiciona pontos de experiência ao Pokémon e verifica se ele pode subir de nível. [cite: 85]
     * @param pontosGanhos A quantidade de pontos a serem adicionados.
     */
    public void aumentarPontosDeExperiencia(int pontosGanhos) {
        this.pontosDeExperiencia += pontosGanhos;
        System.out.println(this.nome + " ganhou " + pontosGanhos + " de experiência!");
        avancarDeNivel();
    }

    /**
     * [cite_start]Verifica se o Pokémon atingiu a XP necessária e, se sim, sobe de nível. [cite: 86]
     */
    private void avancarDeNivel() {
        int xpParaProximoNivel = this.nivel * 100;
        if (this.pontosDeExperiencia >= xpParaProximoNivel) {
            this.nivel++;
            this.forca += 5;
            this.energiaMaxima += 10;
            this.energia = this.energiaMaxima;
            System.out.println("Uau! " + this.nome + " subiu para o nível " + this.nivel + "!");
        }
    }

    /**
     * Causa dano ao Pokémon, diminuindo sua energia. Pode ser sobrescrito.
     * @param dano O dano a ser recebido.
     */
    public void receberDano(int dano) {
        this.energia -= dano;
        if (this.energia < 0) {
            this.energia = 0;
        }
    }

    /**
     * [cite_start]Restaura a energia do Pokémon para seu valor máximo. [cite: 128]
     */
    public void restaurarEnergia() {
        this.energia = this.energiaMaxima;
    }

    // --- Getters e Setters ---
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public int getEnergia() { return energia; }
    public int getForca() { return forca; }
    public int getPontosDeExperiencia() { return pontosDeExperiencia; }
    public int getNivel() { return nivel; }
    public boolean isSelvagem() { return selvagem; }
    public void setSelvagem(boolean selvagem) { this.selvagem = selvagem; }
    public int getEnergiaMaxima() { return energiaMaxima; }
    public boolean isParalisado() { return paralisado; }
    public void setParalisado(boolean paralisado) { this.paralisado = paralisado; }
    protected void setEnergia(int energia) { this.energia = energia; }
}