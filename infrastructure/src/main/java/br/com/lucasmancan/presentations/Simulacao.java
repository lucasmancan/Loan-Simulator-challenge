package br.com.lucasmancan.presentations;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public final class Simulacao {

    @NotNull(message = "Data do primeiro vencimento é obrigatória.")
    private LocalDate primeiroVencimento;

    @NotNull(message = "Taxa de juros é obrigatória.")
    private BigDecimal taxaJuros;

    @NotNull(message = "Valor de simulação do emprestimo é obrigatório.")
    @Min(value = 0, message = "O valor do emprestimo deve ser maior que zero")
    private BigDecimal valorEmprestimo;

    @NotNull(message = "Prazo de pagamento do emprestimo é obrigatório.")
    @Min(value = 0, message = "O prazo de pagamento deve ser maior que zero")
    private Integer prazo;

    public Simulacao() {
    }

    public LocalDate getPrimeiroVencimento() {
        return primeiroVencimento;
    }

    public void setPrimeiroVencimento(LocalDate primeiroVencimento) {
        this.primeiroVencimento = primeiroVencimento;
    }

    public BigDecimal getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(BigDecimal taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public BigDecimal getValorEmprestimo() {
        return valorEmprestimo;
    }

    public void setValorEmprestimo(BigDecimal valorEmprestimo) {
        this.valorEmprestimo = valorEmprestimo;
    }

    public Integer getPrazo() {
        return prazo;
    }

    public void setPrazo(Integer prazo) {
        this.prazo = prazo;
    }
}
