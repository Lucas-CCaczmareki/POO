public class Funcionario {
    //Atributos
    String nome;
    String departamento;
    Double salario;
    String dataEntrada;
    String rg;

    //Métodos
    void recebeAumento(double aumento){
        salario += aumento;
    }
    
    
    void calculaGanhoAnual(){
        System.out.println("\nO salário atual do " + nome + " é de: " + salario*12);
    }

    
    void mostra(){
        System.out.println("-------------------------------------");
        System.out.println("Nome: " + nome);
        System.out.println("Departamento: " + departamento);
        System.out.println("Salário: " + salario);
        System.out.println("Data de entrada: " + dataEntrada);
        System.out.println("RG: " + rg);
        System.out.println("-------------------------------------");
    }
    
    public static void main(String args[]){
        Funcionario Shadow;
        Funcionario Eggman;

        Shadow = new Funcionario();
        Eggman = new Funcionario();

        //------------ Inserindo Dados -----------------
        Shadow.nome = "Shadow o ourico";
        Shadow.departamento = "Viloes";
        Shadow.salario = 5000.0;
        Shadow.dataEntrada = "01/04/2025";
        Shadow.rg = "1234567869";

        Eggman.nome = "Dr. Ivo Robotnik";
        Eggman.departamento = "Pesquisas";
        Eggman.salario = 10000.8; //parecem duas bolas e um torpeto (com muita imaginação)
        Eggman.dataEntrada = "05/01/2025";
        Eggman.rg = "987654321";

        //------------- Testando métodos -----------------
        //Aumento no salário
        Eggman.recebeAumento(500);
        System.out.println("Com um aumento de 500 reais, o salário do DrEggman fica: " + Eggman.salario);
        
        //Ganho anual
        Eggman.calculaGanhoAnual();
        Shadow.calculaGanhoAnual();

        //Printando dados
        Eggman.mostra();
        Shadow.mostra();
    }
}
