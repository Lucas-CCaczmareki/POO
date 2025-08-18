package projetofinal;

/*
 * Define um contrato para qualquer objeto que queira observar eventos do jogo
 * Classes que implementarem essa interface podem ser avisadas quando algo importante acontece
 * 
 * Ela implementa o padrão Observer, que permite que várias partes do sistema sejam 
 * notificadas automaticamente quando algo acontece.
 */
public interface ObservadorJogo {

    /**
     * @param evento Uma String que descreve o que aconteceu (ex: "JOGADA_CONCLUIDA", "POKEMON_CAPTURADO").
     * @param dados Um objeto opcional com informações extras sobre o evento (ex: o Pokémon que foi capturado).
     */
    void atualizar(String evento, Object dados);
}