package ex1.pkg4;

public class Tarefa {
    //Atributos
    private String descricao;
    private String dataVencimento;  //formato esperado: "DD/MM/AAAA"
    private boolean concluida;

    //Construtor
    public Tarefa(String descricao, String dataVencimento) {
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
        this.concluida = false;
    }

    //Métodos
    public void marcarComoConluida(){
        this.concluida = true;
    }

    public void exibirDetalhes() {
        System.out.println("--------------------------------------");
        System.out.println(descricao);
        System.out.println("Data de vencimento: " + dataVencimento);
        if (concluida) {
            System.out.println("Status: Concluída");   
        } else System.out.println("Status: Em progresso");

        System.out.println("--------------------------------------");

    }
}
