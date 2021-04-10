package encargo.usecases;


import common.SimulacaoEmprestimo;
import encargo.Encargo;

import java.util.List;

public interface ApuradorEncargo {
    List<Encargo> apurar(SimulacaoEmprestimo informacaoPagamento);
}
