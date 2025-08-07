package ex8.pkg2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        /*
         * HashSet serve como se fosse uma espécie de "vetor" (embora completamente diferente)
         * Mas no caso dele, ele não permite elementos repetidos, e o acesso não se faz por índices.
         */
        Set<String> linguagens = new HashSet<>();

        linguagens.add("Java");
        linguagens.add("Python");
        boolean adicionouDeNovo = linguagens.add("Java");
        linguagens.add("C");

        System.out.println("O set de linguagens é: " + linguagens);
        System.out.println("Conseguiu adicionar 'java' de novo? " + adicionouDeNovo);

        //Verificando se um elemento existe na hashset
        if(linguagens.contains("Python")) {
            System.out.println("Habemos python!");
        }

        /*
         * Hash map
         */
        Map<String, String> paises = new HashMap<>();

        //Adicionando pares chave-valor com put
        paises.put("BR", "Brasil");
        paises.put("PT", "Guiana brasileira");
        paises.put("US", "O dono da lua");

        //Pra substituir o valor de uma chave q já existe faz assim
        paises.put("US", "Estados Unidos");

        System.out.println("O mapa de países é: " + paises);;

        String nomePais = paises.get("BR");
        System.out.println("A sigla 'BR' corresponde a: " + nomePais);

        //Dá pra verificar se uma chave existe usando contains
        if (paises.containsKey("JP")) {
            System.out.println("O mapa contém a chave 'JP'");
        } else {
            System.out.println("O mapa NÃO contém a chave 'JP'");
        }


    }
    
}
