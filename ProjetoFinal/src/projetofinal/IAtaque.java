package projetofinal;

/**
 * INFORMAÇÕES IMPORTANTES: Deve fazer uso de interface
 * Interface que define o contrato para qualquer entidade que possa atacar.
 * Exige a implementação de um método para calcular o dano.
 */
public interface IAtaque {

    /**
     * Calcula o dano de um ataque.
     * @param numeroDoTurno O número do turno atual, necessário para habilidades
     * especiais como a do Pokémon de Terra.
     * @return O valor do dano calculado.
     */
    int calcularDano(int numeroDoTurno);
}
