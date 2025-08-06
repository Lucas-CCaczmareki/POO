package ex6.pkg4;

public class PagamentoPix extends Pagamentos{
    //Atributos
    String chavePix;

    //Construtor
    public PagamentoPix(double valor, String data, String chavePix) {
        super(valor, data);
        this.chavePix = chavePix;
    }

    //Métodos
    @Override
    public void realizarPagamento() {
        System.out.println("Pix para a chave " + chavePix + " realizado com sucesso!");
    }
    
}
