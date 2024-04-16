package com.voll.med.api.domain.consulta.validation;

import com.voll.med.api.domain.consulta.ConsultaRepository;
import com.voll.med.api.domain.consulta.DadosAgendamentoConsulta;
import com.voll.med.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoConsultaNoMesmoHorario implements ValidadorAgendamentoConsultas {

    ConsultaRepository agendaRepository;

    public ValidadorMedicoConsultaNoMesmoHorario(ConsultaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }
    public void validar(DadosAgendamentoConsulta dados) {
        var medicoPossuiConsulta = agendaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data());

        if (medicoPossuiConsulta) {
            throw new ValidacaoException("Medico possui consulta neste horário");
        }

    }
}
