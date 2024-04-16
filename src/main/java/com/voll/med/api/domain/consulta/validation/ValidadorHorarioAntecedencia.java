package com.voll.med.api.domain.consulta.validation;

import com.voll.med.api.domain.consulta.DadosAgendamentoConsulta;
import com.voll.med.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoConsultas {

    public void validar(DadosAgendamentoConsulta dados) {

        var dataConsulta = dados.data();
        var dataAtual = LocalDateTime.now();
        var diferencaMinutos = Duration.between(dataAtual, dataConsulta).toMinutes();

        if (diferencaMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada pelo menos de 30 minutos");
        }
    }
}
