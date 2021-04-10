package encargo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EncargoEntity {
    private EncargoTipo tipo;
    private BigDecimal valorFixo;
    private BigDecimal valorRecorrente;
    private FrequenciaRecorrencia frequenciaRecorrencia;
}
