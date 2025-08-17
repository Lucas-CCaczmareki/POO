package projetofinal;

public interface IAtaque {
    /**
     * Calcula o dano de um ataque.
     * @param numeroDoTurno O número do turno atual, essencial para habilidades
     * especiais como a do Pokémon de Terra.
     * @return O valor do dano calculado.
     */
    int calcularDano(int numeroDoTurno);
}
