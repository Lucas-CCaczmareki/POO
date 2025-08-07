package ex9.pkg4;

public class NotaInvalidaException extends RuntimeException {
    public NotaInvalidaException(String mensagem) {
        super(mensagem);
    }
    public NotaInvalidaException(){
        super("Nota digitada fora dos parâmetros");
    }
}
