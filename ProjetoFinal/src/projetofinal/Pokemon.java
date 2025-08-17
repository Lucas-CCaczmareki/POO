package projetofinal;
import java.io.Serializable;


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

    
    @Override
    public abstract int calcularDano(int numeroDoTurno);

    public void aumentarPontosDeExperiencia(int pontos) {
        this.pontosDeExperiencia += pontos;
        if (this.pontosDeExperiencia >= this.nivel * 100) {
            this.avancarNivel();
        }
    }

    private void avancarNivel() {
        this.nivel++;
        this.forca += 10;
        this.energiaMaxima += 20; 
        this.energia = this.energiaMaxima; 
        System.out.println(nome + " subiu para o nível " + nivel + "!");
    }

    public void receberDano(int dano) {
        this.energia -= dano;
        if (this.energia < 0) this.energia = 0;
    }

    public void curar(int pontos) {
        this.energia += pontos;
        if (this.energia > this.energiaMaxima) this.energia = this.energiaMaxima;
    }

    public void restaurarEnergia() {
        
        this.energia = this.energiaMaxima;
    }

    
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public int getEnergia() { return energia; }
    public int getEnergiaMaxima() { return energiaMaxima; }
    public int getForca() { return forca; }
    public int getPontosDeExperiencia() { return pontosDeExperiencia; }
    public int getNivel() { return nivel; }
    public boolean isSelvagem() { return selvagem; }
    public boolean isParalisado() { return paralisado; }

    
    protected void setEnergia(int energia) { this.energia = energia; }
    protected void setForca(int forca) { this.forca = forca; }
    
    public void setSelvagem(boolean selvagem) { this.selvagem = selvagem; }
    public void setParalisado(boolean paralisado) { this.paralisado = paralisado; }

    @Override
    public String toString() {
        return nome + " (Nível " + nivel + ", " + tipo + ") - Energia: " + energia + "/" + energiaMaxima;
    }
}