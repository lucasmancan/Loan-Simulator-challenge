package common;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Value
public class SimulacaoEmprestimo {
    LocalDate primeiroVencimento;
    BigDecimal taxaJuros;
    BigDecimal valorEmprestimo;
    Integer prazo;
}
