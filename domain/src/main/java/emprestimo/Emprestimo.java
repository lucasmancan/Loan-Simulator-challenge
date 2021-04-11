package emprestimo;


import encargo.Encargo;
import lombok.*;
import parcela.Parcela;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Emprestimo {
    private final List<Encargo> encargos = new ArrayList<>();
    private final List<Parcela> parcelas = new ArrayList<>();
    private BigDecimal valorSolicitado;
    private Integer quantidadeMeses;
    private BigDecimal valorTotal;
    private BigDecimal valorJuros;

    public Emprestimo(BigDecimal valor, Integer quantidadeMeses) {
        this.valorSolicitado = valor;
        this.quantidadeMeses = quantidadeMeses;
    }

    public Emprestimo addEncargos(List<Encargo> encargos) {
        this.encargos.addAll(encargos);
        return this;
    }

    public Emprestimo addParcelas(List<Parcela> parcelas) {
        this.parcelas.addAll(parcelas);
        return this;
    }

    public Emprestimo valorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
        return this;
    }

    public Emprestimo comJuros(BigDecimal valor) {
        this.valorJuros = valor;
        return this;
    }
}
