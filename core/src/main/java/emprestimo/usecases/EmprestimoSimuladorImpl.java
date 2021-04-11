package emprestimo.usecases;

import common.SimulacaoEmprestimo;
import emprestimo.Emprestimo;
import encargo.Encargo;
import encargo.usecases.ApuradorEncargo;
import parcela.Parcela;
import parcela.usecases.GeradorParcelas;

import java.math.BigDecimal;

public class EmprestimoSimuladorImpl implements EmprestimoSimulador {

    private final ApuradorEncargo apuradorEncargo;
    private final GeradorParcelas geradorParcelas;

    public EmprestimoSimuladorImpl(ApuradorEncargo apuradorEncargo, GeradorParcelas geradorParcelas) {
        this.apuradorEncargo = apuradorEncargo;
        this.geradorParcelas = geradorParcelas;
    }

    @Override
    public Emprestimo simular(SimulacaoEmprestimo simulacaoEmprestimo) {

        var emprestimo = new Emprestimo(simulacaoEmprestimo.getValorEmprestimo(),
                simulacaoEmprestimo.getQuantidadeMeses());

        var encargos = this.apuradorEncargo.apurar(simulacaoEmprestimo);
        var parcelas = this.geradorParcelas.gerarParcelas(simulacaoEmprestimo);

        var valorTotalEncargos = encargos.stream()
                .map(Encargo::getValor)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        var valorTotalParcelas = parcelas.stream()
                .map(Parcela::getValor)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        return emprestimo
                .addEncargos(encargos)
                .addParcelas(parcelas)
                .valorTotal(valorTotalEncargos.add(valorTotalParcelas))
                .comJuros(valorTotalParcelas.subtract(simulacaoEmprestimo.getValorEmprestimo()));
    }


}
