package emprestimo.usecases;


import common.SimulacaoEmprestimo;
import emprestimo.Emprestimo;

public interface EmprestimoSimulador {
    Emprestimo simular(SimulacaoEmprestimo informacaoPagamento);
}
