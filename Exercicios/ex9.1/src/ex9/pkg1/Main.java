package ex9.pkg1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite sua idade: ");
        
        try {
            int idade = scanner.nextInt();
            System.out.println("Você digitou sua idade!!! Ela é: " + idade + " anos");
            
        } catch (Exception NumberFormatException) {
            System.out.println("O FILHO DA PUTA DIGITA O NUMERO CARALHO");
        }
        scanner.close();
    }
    
}
