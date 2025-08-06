package ex6.pkg4;

public class Main {
    public static void main(String[] args) {
        ProcessadorPagamentos processaPag = new ProcessadorPagamentos();
        PagamentoBoleto boleto = new PagamentoBoleto(5000, "06/08/25", "78945454569");
        PagamentoCartaoCredito cartao = new PagamentoCartaoCredito(300, "25/07/25", "5438321000", 3);
        PagamentoPix pix = new PagamentoPix(1000, "05/08/25", "02034499085");

        //Aqui dá pra ver o polimorfismo em ação. Processar recebe um pagamento, ele pode assumir a forma de qualquer uma das subclasses
        processaPag.processar(boleto);
        processaPag.processar(cartao);
        processaPag.processar(pix);
    }
    
}
