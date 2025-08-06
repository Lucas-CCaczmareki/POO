package ex6.pkg4;

public class PagamentoCartaoCredito extends Pagamentos {
    //Atributos
    private String numeroCartao;
    private int parcelas;

    //Construtor
    public PagamentoCartaoCredito(double valor, String data, String numeroCartao, int parcelas){
        super(valor, data);
        this.numeroCartao = numeroCartao;
        this.parcelas = parcelas;
    }

    @Override
    public void realizarPagamento(){
        System.out.println("O pagamento será realizado em " + parcelas + " de R$" + getValor()/parcelas + " no cartão " + numeroCartao);
    }

    
}
