/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicio03;

/**
 *
 * @author lucas
 */
public class controle_remoto {
    //Atributos
    private televisao tv;
    
    //Construtores
    public controle_remoto(televisao tv) {
        this.tv = tv;
    }
    
    //Métodos
    public void diminuirCanal() {
        tv.diminuirCanal();
    }
    
    public void aumentarCanal() {
        tv.aumentarCanal();
    }   
    
    public void diminuirVolume(){
        tv.diminuirVolume();
    }
    
    public void aumentarVolume() {
        tv.aumentarVolume();
    }
    
    public void mostrarInfo() {
        tv.mostrarInfo();
    }
    
    public void trocarCanal(int destino) {
        tv.trocarCanal(destino);
    }
}
