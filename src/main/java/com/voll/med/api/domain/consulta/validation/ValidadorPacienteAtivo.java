package com.voll.med.api.domain.consulta.validation;

import com.voll.med.api.domain.consulta.DadosAgendamentoConsulta;
import com.voll.med.api.domain.paciente.PacienteRepository;
import com.voll.med.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsultas {
    private PacienteRepository pacienteRepository;

    public ValidadorPacienteAtivo(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public void validar(DadosAgendamentoConsulta dados) {

        var pacienteEstaAtivo = pacienteRepository.existsByIdAndAtivoIsTrue(dados.idPaciente());

        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Paciente inativo");

        }
    }

}
