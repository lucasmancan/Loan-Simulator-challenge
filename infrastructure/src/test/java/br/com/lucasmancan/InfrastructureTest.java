package br.com.lucasmancan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.SimulacaoEmprestimo;
import emprestimo.Emprestimo;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;

@MicronautTest
class InfrastructureTest {

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    @Client("/")
    RxHttpClient client;

    @Inject
    private ObjectMapper mapper;

    @Test
    void testItWorks() throws JsonProcessingException {

        SimulacaoEmprestimo simulacaoEmprestimo = SimulacaoEmprestimo.builder()
                .prazo(12)
                .primeiroVencimento(LocalDate.now().plusMonths(1))
                .taxaJuros(new BigDecimal("0.01"))
                .valorEmprestimo(new BigDecimal(10000))
                .build();

        var result = client.toBlocking().retrieve(HttpRequest.POST("v1/emprestimos", simulacaoEmprestimo));

        Emprestimo emprestimo = mapper.readValue(result, Emprestimo.class);

        Assertions.assertTrue(application.isRunning());
        Assertions.assertEquals(0, emprestimo.getValorSolicitado().compareTo(simulacaoEmprestimo.getValorEmprestimo()));
        Assertions.assertEquals(12, emprestimo.getPrazo());
        Assertions.assertEquals(0, emprestimo.getValorTotal().compareTo(new BigDecimal("14464.85")));
        Assertions.assertEquals(0, emprestimo.getValorJuros().compareTo(new BigDecimal("661.85")));
    }

}
