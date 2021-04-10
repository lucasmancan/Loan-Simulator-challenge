package br.com.lucasmancan.converters;

import br.com.lucasmancan.presentations.Simulacao;
import common.SimulacaoEmprestimo;

import javax.inject.Singleton;

@Singleton
public class SimulacaoConverter implements Converter<SimulacaoEmprestimo, Simulacao>{
    @Override
    public SimulacaoEmprestimo toDomain(Simulacao data) {
        return SimulacaoEmprestimo.builder()
                .prazo(data.getPrazo())
                .primeiroVencimento(data.getPrimeiroVencimento())
                .valorEmprestimo(data.getValorEmprestimo())
                .taxaJuros(data.getTaxaJuros())
                .build();
    }

    @Override
    public Simulacao toDTO(SimulacaoEmprestimo data) {
        return null;
    }
}
