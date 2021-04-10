package encargo;


import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Encargo {
    private EncargoTipo tipo;
    private BigDecimal valor;
}
