/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p2_ex4;

/**
 *
 * @author lucas
 */
public class relogio {
    //Atributors
    private contador minutos;
    private contador horas;
    
    //Método construtor
    public relogio() {
        //Relação de composição (o relógio é composto por 2 contadores)
        minutos = new contador(60);
        horas = new contador(24);
    }
    
    public relogio(int minuto, int hora) {
        minutos = new contador(minuto, 60);
        horas = new contador(hora, 24);
    }
    
    public void AtualizaVisor() {
        System.out.println( horas.DevolveString() + ":" + minutos.DevolveString() );
    }
    
    public void EventoDoRelogio() {
        minutos.Incremento();
        if ( minutos.DevolveValor() == 0 ) {
            horas.Incremento();
        }
    }
    
    public void alterarHora(int hora) {
        horas.AlterarValor(hora);
    }
    
    public void alterarMinuto(int minuto) {
        minutos.AlterarValor(minuto);
    }
    
}
