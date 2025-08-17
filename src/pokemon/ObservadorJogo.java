package pokemon;

/**
 * Interface para o padrão de projeto Observer.
 * Qualquer classe que queira ser notificada sobre mudanças no estado do jogo
 * (como a JanelaJogo) deve implementar esta interface.
 */
public interface ObservadorJogo {

    /**
     * Método chamado pelo 'Observável' (a classe Jogo) quando seu estado muda.
     * @param evento Uma String que descreve o que aconteceu (ex: "JOGADA_CONCLUIDA", "POKEMON_CAPTURADO").
     * @param dados Um objeto opcional com informações extras sobre o evento (ex: o Pokémon que foi capturado).
     */
    void atualizar(String evento, Object dados);
}