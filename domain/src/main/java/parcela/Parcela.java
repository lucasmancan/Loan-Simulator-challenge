package parcela;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Parcela {
    private BigDecimal valor;
    private BigDecimal juros;
    private BigDecimal amortizacao;
    private BigDecimal saldoDevedor;
    private LocalDate venceEm;
}