package encargo.usecases;


import common.SimulacaoEmprestimo;
import encargo.Encargo;
import encargo.FrequenciaRecorrencia;
import encargo.repositories.EncargoRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class ApuradorEncargoImpl implements ApuradorEncargo {

    private final EncargoRepository encargoRepository;

    public ApuradorEncargoImpl(EncargoRepository encargoRepository) {
        this.encargoRepository = encargoRepository;
    }

    @Override
    public List<Encargo> apurar(SimulacaoEmprestimo simulacaoEmprestimo) {
        return encargoRepository
                .buscarTaxasAtivas().stream().map(encargoEntity -> {

                    BigDecimal valorFixo = simulacaoEmprestimo
                            .getValorEmprestimo()
                            .multiply(encargoEntity.getValorFixo());

                    long diferencaEntreDatas = calcularDiferencaEntreDatas(simulacaoEmprestimo.getPrimeiroVencimento(),
                            simulacaoEmprestimo.getPrazo(),
                            encargoEntity.getFrequenciaRecorrencia());

                    BigDecimal valorTotalRecorrente = encargoEntity.getValorRecorrente()
                            .multiply(BigDecimal.valueOf(diferencaEntreDatas));

                    return Encargo.builder()
                            .tipo(encargoEntity.getTipo())
                            .valor(valorFixo.add(valorTotalRecorrente))
                            .build();
                }).collect(Collectors.toList());
    }

    private long calcularDiferencaEntreDatas(LocalDate dataPrimeiroVencimento,
                                             Integer prazoPagamento,
                                             FrequenciaRecorrencia frequenciaRecorrencia) {

        LocalDate dataFinal = dataPrimeiroVencimento.plusMonths(prazoPagamento);

        var chronoUnit = fromFrequencytoChronoUnit(frequenciaRecorrencia);

        return chronoUnit.between(dataPrimeiroVencimento, dataFinal);
    }

    private ChronoUnit fromFrequencytoChronoUnit(FrequenciaRecorrencia frequenciaRecorrencia) {
        ChronoUnit chronoUnit = null;

        switch (frequenciaRecorrencia) {
            case ANO:
                chronoUnit = ChronoUnit.YEARS;
                break;
            case DIA:
                chronoUnit = ChronoUnit.DAYS;
                break;
            case MES:
                chronoUnit = ChronoUnit.MONTHS;
                break;
            default:
                throw new IllegalArgumentException(format("There's no time unit equivalent to %s", frequenciaRecorrencia.name()));
        }

        return chronoUnit;
    }
}
