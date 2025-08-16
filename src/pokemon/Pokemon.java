package pokemon;

import java.io.Serializable;
import java.util.Random;

public abstract class Pokemon implements IAtaque, Serializable {
    protected int energia;
    protected int forca;
    protected int experiencia;
    protected int nivel;
    protected String nome;
    protected boolean selvagem;
    protected String tipo;
    protected Random random;
    
    public Pokemon(String nome, String tipo, int forca) {
        this.nome = nome;
        this.tipo = tipo;
        this.forca = forca;
        this.energia = 100;
        this.experiencia = 0;
        this.nivel = 1;
        this.selvagem = true;
        this.random = new Random();
    }
    
    public abstract int calcularDano();
    
    public void aumentarExperiencia(int pontos) {
        this.experiencia += pontos;
        if (this.experiencia >= this.nivel * 100) {
            this.avancarNivel();
        }
    }
    
    public void avancarNivel() {
        this.nivel++;
        this.forca += 10;
        this.energia = 100;
        System.out.println(nome + " subiu para o nivel " + nivel + "!");
    }
    
    public void receberDano(int dano) {
        this.energia -= dano;
        if (this.energia < 0) this.energia = 0;
    }
    
    public void curar(int pontos) {
        this.energia += pontos;
        if (this.energia > 100) this.energia = 100;
    }
    
    public void restaurarEnergia() {
        this.energia = 100;
    }
    
    // Getters e Setters
    public int getEnergia() { return energia; }
    public int getForca() { return forca; }
    public int getExperiencia() { return experiencia; }
    public int getNivel() { return nivel; }
    public String getNome() { return nome; }
    public boolean isSelvagem() { return selvagem; }
    public String getTipo() { return tipo; }
    public Random getRandom() { return random; }
    
    public void setSelvagem(boolean selvagem) { this.selvagem = selvagem; }
    
    @Override
    public String toString() {
        return nome + " (Nível " + nivel + ", " + tipo + ") - Energia: " + energia;
    }
}
