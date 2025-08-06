package ex6.pkg4;

public class PagamentoBoleto extends Pagamentos {
    //Atributos
    private String codigoBarras;

    //Construtor
    public PagamentoBoleto(double valor, String data, String codigoBarras) {
        super(valor, data);
        this.codigoBarras = codigoBarras;
    }

    //Métodos
    @Override
    public void realizarPagamento() {
        System.out.println("Boleto com código " + codigoBarras + " pago!");
    }
}
