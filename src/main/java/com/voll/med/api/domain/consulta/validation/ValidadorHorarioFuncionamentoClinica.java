package com.voll.med.api.domain.consulta.validation;

import com.voll.med.api.domain.consulta.DadosAgendamentoConsulta;
import com.voll.med.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsultas {
    public void validar(DadosAgendamentoConsulta dados) {

        if (dados.data().getHour() < 8 || dados.data().getHour() > 19) {
            throw new ValidacaoException("Consulta deve ser agendada entre 8 e 17");
        }
        if (dados.data().getDayOfWeek().equals(DayOfWeek.SUNDAY) || dados.data().getHour() > 18){
            throw new ValidacaoException("Consulta em domingo ou hora superior a 18h não pode ser agendada");
        }

    }
}
