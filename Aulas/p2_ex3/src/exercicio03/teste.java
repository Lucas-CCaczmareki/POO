/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exercicio03;

/*
 *Crie uma classe Televisao e uma classe ControleRemoto que pode
controlar o volume e trocar os canais da televisão. O controle de volume permite:

    ● aumentar ou diminuir a potência do volume de som em uma unidade de cada
        vez;
    ● aumentar e diminuir o número do canal em uma unidade;
    ● trocar para um canal indicado;
    ● consultar o valor do volume de som e o canal selecionado.
    * @author lucas
 */

public class teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        televisao tv;
        tv = new televisao();
        tv.mostrarInfo();
        
        //já declara e cria numa linha só
        controle_remoto controle = new controle_remoto(tv);
        controle.mostrarInfo();
        controle.aumentarCanal();
        controle.mostrarInfo();
        controle.trocarCanal(80);
        tv.mostrarInfo();
        
    }
    
}
