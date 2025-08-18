package projetofinal;
import java.io.Serializable;

/*
 *  Serializable permite que os objetos dessa classe podem ser convertidos
 *  em uma sequência de bytes. É isso que permite salvar o estado dos pokémons em um arquivo
 */

//Classe genérica de pokémons, não vai ser instanciada
public abstract class Pokemon implements IAtaque, Serializable {

    //Atributos    
    private String nome;
    private String tipo;
    private int energia;
    private int energiaMaxima; 
    private int forca;
    private int pontosDeExperiencia;
    private int nivel;
    private boolean selvagem;
    private boolean paralisado; 

    //Construtor
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

    //Métodos

    //Repassa a responsabilidade de implementar o calcularDano para as classes filhas
    @Override
    public abstract int calcularDano(int numeroDoTurno);

    public void aumentarPontosDeExperiencia(int pontos) {
        this.pontosDeExperiencia += pontos;
        if (this.pontosDeExperiencia >= this.nivel * 100) {
            this.avancarNivel();
        }
    }

    /*
     *  Fortalece os atributos de um pokémon quando este aumenta seu nível
     */
    private void avancarNivel() {
        this.nivel++;
        this.forca += 10;
        this.energiaMaxima += 20; 
        this.energia = this.energiaMaxima; 
        System.out.println(nome + " subiu para o nível " + nivel + "!");
    }

    /*
     *  Atualiza o valor da vida quando o pokémon sofre um ataque
     *  + Garante que a energia não vá abaixo de 0.
     */
    public void receberDano(int dano) {
        this.energia -= dano;
        if (this.energia < 0) this.energia = 0;
    }

    /*
     * Atualiza o valor da vida quando um pokémon com uma habilidade específica se cura
     * + Garante que a energia não ultrapasse a energia máxima (vida)
     */
    public void curar(int pontos) {
        this.energia += pontos;
        if (this.energia > this.energiaMaxima) this.energia = this.energiaMaxima;
    }

    /*
     *  Método chamado ao final de uma batalha para restaurar toda a energia de um Pokémon
     */
    public void restaurarEnergia() {
        this.energia = this.energiaMaxima;
    }

    //Getters
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public int getEnergia() { return energia; }
    public int getEnergiaMaxima() { return energiaMaxima; }
    public int getForca() { return forca; }
    public int getPontosDeExperiencia() { return pontosDeExperiencia; }
    public int getNivel() { return nivel; }
    public boolean isSelvagem() { return selvagem; }
    public boolean isParalisado() { return paralisado; }

    //Setters
    protected void setEnergia(int energia) { this.energia = energia; }
    protected void setForca(int forca) { this.forca = forca; }
    
    public void setSelvagem(boolean selvagem) { this.selvagem = selvagem; }
    public void setParalisado(boolean paralisado) { this.paralisado = paralisado; }

    @Override
    public String toString() {
        return nome + " (Nível " + nivel + ", " + tipo + ") - Energia: " + energia + "/" + energiaMaxima;
    }
}