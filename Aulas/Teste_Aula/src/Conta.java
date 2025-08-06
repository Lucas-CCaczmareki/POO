/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lucas
 */

import java.util.Scanner;

public class Conta {
    // Atributos
    int numero;
    String dono;
    double saldo;
    double limite;
    
    
    // Métodos
    void deposita(double quantidade) {
        saldo = saldo + quantidade;
    }
    
    boolean saca(double valor) {
        if (valor > saldo + limite) {
            return false;
        } else {
            saldo = saldo - valor;
            return true;
        }
    }
    
    void imprimeDono(){
        System.out.println("Dono: " + dono);
    }
    
    void devolveSaldo(){
        System.out.println("Saldo: " + saldo);
    }
    
    void transferePara(Conta destino, double valor){
        if(saca(valor)){
            destino.deposita(valor);
        } else {
            System.out.println("Saldo insuficiente!");
        }
        
    }
    
    void imprimeDados(){
        System.out.println("--------------------------");
        System.out.println("Num: " + numero);
        imprimeDono();
        devolveSaldo();
        System.out.println("Limite: " + limite);
        System.out.println("--------------------------");
    }
    
    //******* Método principal ********
        //Args é igual o ArgC e o ArgV do C (mas eu n tenho ideia do que é isso)
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        Conta maria;
        Conta joao;

        double valorTransf;
        
        maria = new Conta();
        joao = new Conta();
        
        //Define conta maria
        //-----------------------
        
        maria.numero = 0;
        maria.dono = "Maria";
        maria.saldo = 1000;
        maria.limite = 200;
        
        //Define conta João
        //-----------------------
        joao.numero = 1;
        joao.saldo = 0;
        joao.dono = "Joao";
        
        //Testando operações
        System.out.println("Sacando " + 200 + " da conta da maria");
        maria.saca(200); // sacando 200tao da maria

        System.out.println("Quanto voce deseja transferir? ");
        valorTransf = scanner.nextInt();
        System.out.println("Transferindo " + valorTransf + " da conta da Maria");
        maria.transferePara(joao, valorTransf); //transferindo 500 pro joáo
        
        //Imprimindo dados
        maria.imprimeDados();
        joao.imprimeDados();
        
        scanner.close();
        
        //sout + tab autocompleta o tarugo do system.out.println
        //minhaConta.deposita(200);
        //agora a função(método) é atrelada a classe, então não existe esse tipo de "passagem" de parâmetro
        //minhaConta.imprimeDono(); 
        //minhaConta.devolveSaldo();
        //System.out.println("Dono: " + minhaConta.dono);
        //System.out.println("Saldo: " + minhaConta.saldo);
    }
}
