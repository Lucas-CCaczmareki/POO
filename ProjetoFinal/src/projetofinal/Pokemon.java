package projetofinal;

/**
 * Classe abstrata base para todos os Pokémon.
 * [cite_start]Contém atributos e comportamentos comuns. [cite: 74]
 */
public abstract class Pokemon implements IAtaque {
    private String nome;
    private int energia;
    private int forca;
    private int experiencia; // Pontos de experiência. [cite: 77]
    private int nivel;
    private String tipo; // Ex: "água", "terra", etc. [cite: 79]
    private boolean selvagem; // Indica se pertence a um treinador. [cite: 80]

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isSelvagem() {
        return selvagem;
    }

    public void setSelvagem(boolean selvagem) {
        this.selvagem = selvagem;
    }
}
