/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p2_ex4;

import java.util.List;

/**
 *
 * @author lucas
 */
public class teste {
    public static void main( String[] args ) {
        //O segredo tá em pensar no modelo e não fazer blocos grandes de código!!!
        int i;
        relogio relogioDigital;
        relogioDigital = new relogio();
        
        relogioDigital.AtualizaVisor();
        
        relogioDigital.alterarHora(17);
       
        
        for( i = 0; i < 80; i++){
            relogioDigital.EventoDoRelogio();
        }
        relogioDigital.AtualizaVisor();
    }
}
