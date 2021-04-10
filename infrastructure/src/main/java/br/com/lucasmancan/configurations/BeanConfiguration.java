package br.com.lucasmancan.configurations;


import emprestimo.usecases.EmprestimoSimulador;
import emprestimo.usecases.EmprestimoSimuladorImpl;
import encargo.repositories.EncargoRepository;
import encargo.usecases.ApuradorEncargo;
import encargo.usecases.ApuradorEncargoImpl;
import io.micronaut.context.annotation.Factory;
import parcela.usecases.GeradorParcelas;
import parcela.usecases.GeradorParcelasModeloPrice;

import javax.inject.Inject;
import javax.inject.Singleton;

@Factory
public class BeanConfiguration {

    private final EncargoRepository encargoRepository;

    @Inject
    public BeanConfiguration(EncargoRepository encargoRepository) {
        this.encargoRepository = encargoRepository;
    }

    @Singleton
    public ApuradorEncargo getApuradorEncargo() {
        return new ApuradorEncargoImpl(encargoRepository);
    }

    @Singleton
    public GeradorParcelas getGeradorParcelas() {
        return new GeradorParcelasModeloPrice();
    }

    @Singleton
    public EmprestimoSimulador getEmprestimoSimulador() {
        return new EmprestimoSimuladorImpl(getApuradorEncargo(),
                getGeradorParcelas());
    }
}
