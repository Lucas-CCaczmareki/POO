package ex9.pkg4;

public class MatriculaInvalidaException extends RuntimeException {
    public MatriculaInvalidaException() {
        super("A matricula digitada é inválida.");
    }
}
