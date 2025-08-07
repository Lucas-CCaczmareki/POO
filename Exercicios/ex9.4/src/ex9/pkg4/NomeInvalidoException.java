package ex9.pkg4;

/*
 * Usa-se runtimeexception pois seriam erros tratáveis com lógica dentro do programa
 */

public class NomeInvalidoException extends RuntimeException {
    public NomeInvalidoException() {
        super("Nome digitado está vazio!");
    }
}
