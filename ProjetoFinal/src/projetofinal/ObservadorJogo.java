package projetofinal;

/**
 * Interface para o padrão de projeto Observer.
 * Classes que implementam esta interface podem se registrar para "observar"
 * o estado da classe Jogo e ser notificadas quando ele mudar.
 */
public interface ObservadorJogo {

    /**
     * Método chamado pelo Jogo (o Sujeito) para notificar o Observador
     * de que uma atualização é necessária.
     */
    void atualizar(String evento, Object dados);
}
