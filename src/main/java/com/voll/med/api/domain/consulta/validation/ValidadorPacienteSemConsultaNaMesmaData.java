package com.voll.med.api.domain.consulta.validation;

import com.voll.med.api.domain.consulta.ConsultaRepository;
import com.voll.med.api.domain.consulta.DadosAgendamentoConsulta;
import com.voll.med.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Component
public class ValidadorPacienteSemConsultaNaMesmaData implements ValidadorAgendamentoConsultas {
    private ConsultaRepository consultaRepository;

    public ValidadorPacienteSemConsultaNaMesmaData(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public void validar(DadosAgendamentoConsulta dados) {
        LocalDateTime startDate = dados.data().minusDays(1); // Data inicial (24 horas antes)
        LocalDateTime endDate = dados.data(); // Data final (a própria data)

        if (consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(), startDate, endDate)) {
            throw new ValidacaoException("Paciente possui consulta neste horário");
        }
    }
}
