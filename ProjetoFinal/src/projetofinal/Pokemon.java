package projetofinal;
import java.io.Serializable;
//import java.util.Random;

/**
 * Classe abstrata que serve como base para todos os Pokémon.
 * Contém todos os atributos e métodos comuns, corrigidos para seguir
 * TODOS os requisitos do documento e boas práticas de programação.
 */
public abstract class Pokemon implements IAtaque, Serializable {

    // Atributos agora são private para um melhor encapsulamento
    private String nome;
    private String tipo;
    private int energia;
    private int energiaMaxima; // NOVO: Para cura e restauração corretas
    private int forca;
    private int pontosDeExperiencia;
    private int nivel;
    private boolean selvagem;
    private boolean paralisado; // NOVO: Para a habilidade do tipo Elétrico

    /**
     * Construtor mais flexível para criar diferentes Pokémon.
     */
    public Pokemon(String nome, String tipo, int energia, int forca, int nivel) {
        this.nome = nome;
        this.tipo = tipo;
        this.energia = energia;
        this.energiaMaxima = energia; // Energia máxima inicia igual à energia base
        this.forca = forca;
        this.nivel = nivel;
        this.pontosDeExperiencia = 0;
        this.selvagem = true;
        this.paralisado = false;
    }

    /**
     * Assinatura CORRIGIDA do método abstrato da interface IAtaque.
     * Agora recebe o número do turno como parâmetro.
     */
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
        this.energiaMaxima += 20; // Aumenta a energia máxima
        this.energia = this.energiaMaxima; // Restaura para a nova energia máxima
        System.out.println(nome + " subiu para o nível " + nivel + "!");
    }

    public void receberDano(int dano) {
        this.energia -= dano;
        if (this.energia < 0) this.energia = 0;
    }

    public void curar(int pontos) {
        this.energia += pontos;
        // CORRIGIDO: Não pode curar além da energia máxima
        if (this.energia > this.energiaMaxima) this.energia = this.energiaMaxima;
    }

    public void restaurarEnergia() {
        // CORRIGIDO: Restaura para a energia máxima, não para um valor fixo
        this.energia = this.energiaMaxima;
    }

    // --- Getters (públicos) e Setters (protegidos) ---
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public int getEnergia() { return energia; }
    public int getEnergiaMaxima() { return energiaMaxima; }
    public int getForca() { return forca; }
    public int getPontosDeExperiencia() { return pontosDeExperiencia; }
    public int getNivel() { return nivel; }
    public boolean isSelvagem() { return selvagem; }
    public boolean isParalisado() { return paralisado; }

    // Setters são 'protected' para serem acessados apenas por subclasses, se necessário
    protected void setEnergia(int energia) { this.energia = energia; }
    protected void setForca(int forca) { this.forca = forca; }
    
    // Setters que precisam ser acessados de fora (pelo Jogo) permanecem públicos
    public void setSelvagem(boolean selvagem) { this.selvagem = selvagem; }
    public void setParalisado(boolean paralisado) { this.paralisado = paralisado; }

    @Override
    public String toString() {
        return nome + " (Nível " + nivel + ", " + tipo + ") - Energia: " + energia + "/" + energiaMaxima;
    }
}