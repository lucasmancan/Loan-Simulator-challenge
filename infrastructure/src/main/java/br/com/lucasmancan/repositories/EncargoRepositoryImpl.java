package br.com.lucasmancan.repositories;

import encargo.EncargoEntity;
import encargo.EncargoTipo;
import encargo.FrequenciaRecorrencia;
import encargo.repositories.EncargoRepository;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Singleton
public class EncargoRepositoryImpl implements EncargoRepository {
    @Override
    public List<EncargoEntity> buscarTaxasAtivas() {
        var entity = new EncargoEntity();
        entity.setTipo(EncargoTipo.IOF);
        entity.setFrequenciaRecorrencia(FrequenciaRecorrencia.DIA);
        entity.setValorPorcentagemFixa(new BigDecimal("0.38"));
        entity.setValorPorcentagemRecorrente(new BigDecimal("0.0082"));
        return Arrays.asList(entity);
    }
}
