package parcela.usecases;


import common.SimulacaoEmprestimo;
import parcela.Parcela;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GeradorParcelasModeloPrice implements GeradorParcelas {

    /**
     * @param valor total do emprestimo
     * @param juros taxa de juros
     * @param prazo prazo para pagamento do emprestimo em meses
     * @return valor da parcela
     */
    private static BigDecimal calcularValorParcela(BigDecimal valor,
                                                   BigDecimal juros,
                                                   Integer prazo) {
        double valorComum = Math.pow((1 + juros.doubleValue()), prazo);
        double dividendo = valorComum * juros.doubleValue();
        double divisor = valorComum - 1;

        return valor.multiply(BigDecimal.valueOf(dividendo / divisor));
    }

    @Override
    public List<Parcela> gerarParcelas(SimulacaoEmprestimo simulacaoEmprestimo) {
        BigDecimal valorFixoPagamento = calcularValorParcela(simulacaoEmprestimo.getValorEmprestimo(),
                simulacaoEmprestimo.getTaxaJuros(),
                simulacaoEmprestimo.getPrazo());

        var parcelas = new ArrayList<Parcela>();

        var valor = simulacaoEmprestimo.getValorEmprestimo();

        for (int i = 1; i <= simulacaoEmprestimo.getPrazo(); i++) {

            final BigDecimal vlJuros = calcularValorJuros(simulacaoEmprestimo.getTaxaJuros(), valor);
            final BigDecimal vlAmortizacao = calcularValorAmortizacao(valorFixoPagamento, vlJuros);
            final BigDecimal vlSaldoDevedor = calcularSaldoDevedor(valor, vlAmortizacao);

            var parcela = Parcela.builder()
                    .amortizacao(vlAmortizacao)
                    .juros(vlJuros)
                    .saldoDevedor(vlSaldoDevedor)
                    .pagto(valorFixoPagamento)
                    .vencimento(simulacaoEmprestimo.getPrimeiroVencimento().plusMonths(i - 1))
                    .build();

            parcelas.add(parcela);

            valor = vlSaldoDevedor;
        }

        return parcelas;
    }

    private BigDecimal calcularSaldoDevedor(BigDecimal valorEmprestimo, BigDecimal valorAmortizacao) {
        return valorEmprestimo.subtract(valorAmortizacao);
    }

    private BigDecimal calcularValorAmortizacao(BigDecimal valorParcela, BigDecimal valorJuros) {
        return valorParcela.subtract(valorJuros);
    }

    private BigDecimal calcularValorJuros(BigDecimal taxaDeJuros, BigDecimal valorDevido) {
        return valorDevido.multiply(taxaDeJuros);
    }
}
