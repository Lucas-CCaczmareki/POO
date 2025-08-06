/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicio03;

/**
 // public class televisao
    //canal: int
    //volume: int

    //nome            (parametros): valor de retorno
    // + = acesso público
    // - = acesso privado

    //+diminuir volume (): void
    //+aumentar volume (): void
    //+diminuir canal  (): void
    //+aumentar canal  (): void
    //+trocar canal    (): int
    //+mostrar info    (): int
 */
public class televisao {
    //Atributos encapsulados (ou seja, são privados, só podem ser mudados dentro dessa classe)
    private int canal;
    private int volume;
    //static faz com que todas as instâncias utilizem o mesmo espaço de memória. Ela é comum pra todos objetos
    private static final int CANAL_MAX = 400;  //equivale à um const int ou um #define
    
    //Construturor de inicialização
    public televisao( int canal, int volume ) {
        this.canal  = canal;     //é bem intuitivo, this significa que é o canal dessa classe.
        this.volume = volume;
    }
    
    //Construtor padrão
    public televisao() {
        //this.canal  = 1;
        //this.volume = 10;
        this(1, 10); //outra forma de fazer os de cima.
    }
    
    //Métodos
    public void diminuirVolume() {
        if( volume > 0 ) {
            volume --;
        }
    }
    
    public void aumentarVolume() {
        if( volume < 100 ) {
            volume ++;
        }
    }
    
    public void diminuirCanal(){
        if( canal > 1 ) {
            canal --;
        }
    }
    
    public void aumentarCanal() {
        if( canal < CANAL_MAX ) {
            canal ++;
        } 
    }
    
    public int devolverCanal() {
        return canal;
    }
    
    public int devolverVolume() {
        return volume;
    }
    
    public void mostrarInfo() {
        System.out.println( "Canal: " + canal );
        System.out.println( "Volume: " + volume );
    }
    
    public void trocarCanal(int destino) {
        if( destino > 0 && destino <= CANAL_MAX ) {
            canal = destino;
        }
    }
    
}
