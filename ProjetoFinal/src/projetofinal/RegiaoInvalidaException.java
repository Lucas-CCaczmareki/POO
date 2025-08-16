package projetofinal;

/**
 * Exceção personalizada lançada quando há uma tentativa de posicionar
 * um Pokémon fora de sua região designada no tabuleiro.
 */
public class RegiaoInvalidaException extends Exception {
    public RegiaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}