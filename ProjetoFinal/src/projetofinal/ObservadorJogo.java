package projetofinal;


public interface ObservadorJogo {

    /**
     * @param evento Uma String que descreve o que aconteceu (ex: "JOGADA_CONCLUIDA", "POKEMON_CAPTURADO").
     * @param dados Um objeto opcional com informações extras sobre o evento (ex: o Pokémon que foi capturado).
     */
    void atualizar(String evento, Object dados);
}