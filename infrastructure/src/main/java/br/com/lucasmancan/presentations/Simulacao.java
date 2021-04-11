package br.com.lucasmancan.presentations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class Simulacao {

    @NotNull(message = "Data do primeiro vencimento é obrigatória.")
    @Future(message = "Data do primeiro vencimento é inválida.")
    private LocalDate primeiroVencimento;

    @NotNull(message = "Taxa de juros é obrigatória.")
    @DecimalMin(value = "0.01", message = "Taxa de juros mensal mínima é 0.01%")
    @DecimalMax(value = "50.00", message = "Taxa de juros mensal máxima é 50%")
    private BigDecimal taxaJurosMes;

    @NotNull(message = "Valor de simulação do emprestimo é obrigatório.")
    @Min(value = 500, message = "O valor do emprestimo deve ser maior que R$ 500")
    @Max(value = 50000, message = "O valor do emprestimo deve ser menor que R$ 50.000")
    private BigDecimal valorEmprestimo;

    @NotNull(message = "Prazo de pagamento do emprestimo é obrigatório.")
    @Min(value = 1, message = "O prazo de pagamento deve ser maior que 1 mês")
    @Max(value = 48, message = "O prazo de pagamento deve ser menor que 48 meses")
    private Integer quantidadeMeses;
}
