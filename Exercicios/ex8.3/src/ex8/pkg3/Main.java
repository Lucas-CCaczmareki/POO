/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ex8.pkg3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author lucas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Pessoa> listaPessoas = new ArrayList<>();
        
        listaPessoas.add(new Pessoa("Joaozinho", 5));
        listaPessoas.add(new Pessoa("Roberto", 35));
        listaPessoas.add(new Pessoa("Lucas", 20));
        listaPessoas.add(new Pessoa("Tiago", 19));

        System.out.println("--------------- Lista antes da ordenação ---------------");
        for(int i = 0; i < listaPessoas.size(); i++) {
            System.out.println(listaPessoas.get(i).getNome() + " " + listaPessoas.get(i).getIdade());
        }

        Collections.sort(listaPessoas); //o comparte to já vai dizer como que isso é pra ser ordenado

        System.out.println("--------------- Lista APÓS ordenação ---------------");
        for(int i = 0; i < listaPessoas.size(); i++) {
            System.out.println(listaPessoas.get(i).getNome() + " " + listaPessoas.get(i).getIdade());
        }
    }
    
}
