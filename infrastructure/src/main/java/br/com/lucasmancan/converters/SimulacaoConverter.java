package br.com.lucasmancan.converters;

import br.com.lucasmancan.presentations.Simulacao;
import common.SimulacaoEmprestimo;

import javax.inject.Singleton;

@Singleton
public class SimulacaoConverter implements Converter<SimulacaoEmprestimo, Simulacao> {
    @Override
    public SimulacaoEmprestimo toDomain(Simulacao data) {
        return SimulacaoEmprestimo.builder()
                .quantidadeMeses(data.getQuantidadeMeses())
                .primeiroVencimento(data.getPrimeiroVencimento())
                .valorEmprestimo(data.getValorEmprestimo())
                .taxaJurosMes(data.getTaxaJurosMes())
                .build();
    }

    @Override
    public Simulacao toDTO(SimulacaoEmprestimo data) {
        return Simulacao.builder()
                .quantidadeMeses(data.getQuantidadeMeses())
                .primeiroVencimento(data.getPrimeiroVencimento())
                .valorEmprestimo(data.getValorEmprestimo())
                .taxaJurosMes(data.getTaxaJurosMes())
                .build();
    }
}
