package projetofinal;

public abstract class Pokemon implements IAtaque {
    //Atributos
    private String nome;
    private int energia;
    private int energiaMaxima;
    private int forca;
    private int experience = 0;
    private int nivel;
    private String tipo;

    //Atributos de controle
    private boolean selvagem;
    private Treinador dono;
    private String status = "Normal";
    private int turnosSobEfeito = 0;

    //Construtor
    public Pokemon(String nome, String tipo, int energia, int forca, int nivel) {
        this.nome = nome;
        this.tipo = tipo;
        this.energia = energia;
        this.energiaMaxima = energia; // A energia máxima é a energia inicial
        this.forca = forca;
        this.nivel = nivel;
        this.dono = null; // Começa sem dono
        this.selvagem = true; // Todo Pokémon começa como selvagem por padrão
    }

    //Métodos
    public void receberDano(int dano, String ambiente) {
        //Por padrão, o pokémon só toma dano. 
        this.setEnergia(this.getEnergia() - dano);
    }

    public void restaurarEnergia() {
        this.energia = this.energiaMaxima;
        //System.out.println(this.nome + " teve sua energia restaurada!");
    }

    public void passarTurnosSobEfeito() {
        if(!(this.status.equals("Normal"))){ //Se o status é qualquer coisa exceto normal
            this.turnosSobEfeito--;
            if(this.turnosSobEfeito <= 0) {
                this.turnosSobEfeito = 0;
                System.out.println("O pokémon " + this.getNome() + "não está mais sob o efeito: " + this.getStatus());
                this.status = "Normal";
            }
        }
    }

    public boolean isSelvagem() {
        return this.selvagem;
    }

    /*
    * Versão genérica do calcularDano que vai ser sobreescrita pelas classes de pokemon que implementam os tipos
    */
    @Override
    public abstract int calcularDano(Pokemon oponente, int turnoAtual);

    //Getters
    public String getNome() {
        return nome;
    }
    public int getEnergia() {
        return energia;
    }
    public int getExperience() {
        return experience;
    }
    public int getForca() {
        return forca;
    }
    public int getNivel() {
        return nivel;
    }
    public String getTipo() {
        return tipo;
    }
    public String getStatus() {
        return status;
    }
    public int getTurnosSobEfeito() {
        return turnosSobEfeito;
    }
    public Treinador getDono() {
        return dono;
    }

    //Setters
    public void setEnergia(int energia) {
        this.energia = energia;
    }
    public void setExperience(int experience) {
        this.experience += experience;
    }
    public void setForca(int forca) {
        this.forca = forca;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setSelvagem(boolean selvagem) {
        this.selvagem = selvagem;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setStatus(String status) {
        this.status = status;
        if(this.status.equals("Paralisado")) {
            this.turnosSobEfeito += 1;
        }
    }
    public void setTurnosSobEfeito(int turnosSobEfeito) {
        this.turnosSobEfeito = turnosSobEfeito;
    }
    public void setDono(Treinador dono) {
        this.dono = dono;
    }
}
