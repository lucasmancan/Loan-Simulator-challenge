package encargo.usecases;


import common.SimulacaoEmprestimo;
import encargo.Encargo;
import encargo.EncargoEntity;
import encargo.FrequenciaRecorrencia;
import encargo.repositories.EncargoRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ApuradorEncargoImpl implements ApuradorEncargo {

    private final EncargoRepository encargoRepository;

    public ApuradorEncargoImpl(EncargoRepository encargoRepository) {
        this.encargoRepository = encargoRepository;
    }

    @Override
    public List<Encargo> apurar(SimulacaoEmprestimo simulacaoEmprestimo) {

        var encargos = new ArrayList<Encargo>();

        for(EncargoEntity encargoEntity: encargoRepository.buscarTaxasAtivas()){

            BigDecimal valorFixo = simulacaoEmprestimo
                    .getValorEmprestimo()
                    .multiply(encargoEntity.getValorPorcentagemFixa().divide(new BigDecimal("100")));

            long diferencaEntreDatas = calcularDiferencaEntreDatas(simulacaoEmprestimo.getPrimeiroVencimento(),
                    simulacaoEmprestimo.getQuantidadeMeses(),
                    encargoEntity.getFrequenciaRecorrencia());

            BigDecimal valorTotalRecorrente = encargoEntity.getValorPorcentagemRecorrente()
                    .multiply(BigDecimal.valueOf(diferencaEntreDatas))
                    .divide(new BigDecimal("100"))
                    .multiply(simulacaoEmprestimo.getValorEmprestimo());

            encargos.add(Encargo.builder()
                    .tipo(encargoEntity.getTipo())
                    .valor(valorFixo.add(valorTotalRecorrente))
                    .build());
        }

        return encargos;
    }

    private long calcularDiferencaEntreDatas(LocalDate dataPrimeiroVencimento,
                                             Integer prazoPagamento,
                                             FrequenciaRecorrencia frequenciaRecorrencia) {

        LocalDate dataFinal = dataPrimeiroVencimento.plusMonths(prazoPagamento);

        return frequenciaRecorrencia.toChronoUnit()
                .between(dataPrimeiroVencimento, dataFinal);
    }
}
