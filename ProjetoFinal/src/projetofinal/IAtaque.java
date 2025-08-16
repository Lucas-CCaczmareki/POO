package projetofinal;

public interface IAtaque {
    int calcularDano(Pokemon oponente, int turnoAtual);
    /*
     * Fatores de tipo (seguindo a lógica clássica de Pokémon)
     *  Eletrico > Agua
     *  Agua > Terra
     *  Terra > Floresta, Eletrico
     *  Floresta > Terra, Agua
     */
}
