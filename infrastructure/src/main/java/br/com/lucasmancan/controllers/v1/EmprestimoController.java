package br.com.lucasmancan.controllers.v1;

import br.com.lucasmancan.converters.Converter;
import br.com.lucasmancan.presentations.Simulacao;
import common.SimulacaoEmprestimo;
import emprestimo.Emprestimo;
import emprestimo.usecases.EmprestimoSimulador;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import io.swagger.v3.oas.annotations.Operation;

import javax.inject.Inject;
import javax.validation.Valid;

@Validated
@Controller("/v1/emprestimos")
public class EmprestimoController {

    private final EmprestimoSimulador emprestimoSimulador;
    private final Converter<SimulacaoEmprestimo, Simulacao> converter;

    @Inject
    public EmprestimoController(EmprestimoSimulador emprestimoSimulador,
                                Converter<SimulacaoEmprestimo, Simulacao> converter) {
        this.emprestimoSimulador = emprestimoSimulador;
        this.converter = converter;
    }

    @Post(value = "/", produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Cria uma simulação de emprestimo")
    public Emprestimo simular(@Valid @Body Simulacao simulacao) {

        var simulacaoEmprestimo = converter.toDomain(simulacao);

        return emprestimoSimulador.simular(simulacaoEmprestimo);
    }
}
