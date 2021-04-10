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
    BigDecimal pagto;
    BigDecimal juros;
    BigDecimal amortizacao;
    BigDecimal saldoDevedor;
    LocalDate vencimento;
}