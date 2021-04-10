package parcela.usecases;


import common.SimulacaoEmprestimo;
import parcela.Parcela;

import java.util.List;

public interface GeradorParcelas {
    List<Parcela> gerarParcelas(SimulacaoEmprestimo simulacaoEmprestimo);
}
