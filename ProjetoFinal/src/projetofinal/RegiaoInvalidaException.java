package projetofinal;

/*
 * Exceção personalizada que é usada para tratar casos onde o jogador
 * seleciona uma região inválida para posicionar o seu pokémon
 */
public class RegiaoInvalidaException extends Exception {
    public RegiaoInvalidaException(String msg) {
        super(msg);
    }
}
